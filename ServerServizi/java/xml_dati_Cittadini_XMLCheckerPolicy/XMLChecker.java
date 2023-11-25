package xml_dati_Cittadini_XMLCheckerPolicy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wso2.balana.*;
import org.wso2.balana.ctx.AbstractResult;
import org.wso2.balana.ctx.Attribute;
import org.wso2.balana.ctx.ResponseCtx;
import org.wso2.balana.ctx.xacml3.Result;
import org.wso2.balana.finder.AttributeFinder;
import org.wso2.balana.finder.AttributeFinderModule;
import org.wso2.balana.finder.impl.FileBasedPolicyFinderModule;
import org.wso2.balana.xacml3.Attributes;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Web page image filtering sample
 */
public class XMLChecker {

    private static Balana balana;

    public static void main(String[] args) throws Exception{

    	BufferedReader in = new BufferedReader (new InputStreamReader(System.in)); 
        String userName = "";
        String resultResponse = "";
        String action = "";
        String resource = "";

        initBalana();

        System.out.println("\nFollowing are the all static images names that are loaded to web page : \n");
        
        System.out.println("\nWrite the user to check");
        
        userName = in.readLine();
        
        System.out.println("\nWrite the action to check : ");
        
        action = in.readLine();
        
        System.out.println("\nWrite the resource to check : ");
        
        resource = in.readLine();

        if(userName != null && userName.trim().length() > 0
        		&& action != null && action.trim().length() > 0
        		&& resource != null && resource.trim().length() > 0){

            String request = createXACMLRequest(userName.toLowerCase(), action.toLowerCase(), resource.toLowerCase());
            PDP pdp = getPDPNewInstance();

            System.out.println("\n======================== XACML Request ========================");
            System.out.println(request);
            System.out.println("\n===============================================================");

            String response = pdp.evaluate(request);

            System.out.println("\n======================== XACML Response ========================");
            System.out.println(response);
            System.out.println("\n================================================================");

            Set<String> resultResources = new HashSet<String>();

            try {
                ResponseCtx responseCtx = ResponseCtx.getInstance(getXacmlResponse(response));
                Set<AbstractResult> results  = responseCtx.getResults();
                for(AbstractResult result : results){
                    if(AbstractResult.DECISION_PERMIT == result.getDecision()){
                        Set<Attributes> attributesSet = ((Result)result).getAttributes();
                        for(Attributes attributes : attributesSet){
                            for(Attribute attribute : attributes.getAttributes()){
                            	resultResources.add(attribute.getValue().encode());
                            }
                        }
                    }
                }
            } catch (ParsingException e) {
                e.printStackTrace(); 
            }

            if(resultResources.size() > 0){
                System.out.println("\n" + userName + " is authorized to " + action + " " + resource);
                
            	resultResponse = "Permit";
            	System.out.println(resultResponse);
            
                
                System.out.println("\n");
            } else {
                System.out.println("\n" + userName + " is NOT authorized to " + action + " " + resource + "\n");
                resultResponse = "Deny";
            	System.out.println(resultResponse);
            }

        } else {
            System.err.println("\nUser name or action or resource can not be empty\n");                
        }
    }

    private static void initBalana(){

        try{
            // using file based policy repository. so set the policy location as system property
            String policyLocation = (new File("src\\main\\resources\\modify_delete").getCanonicalPath());
            System.out.println("policyLocation: " + policyLocation);
            
            System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, policyLocation);
        } catch (IOException e) {
            System.err.println("Can not locate policy repository");
        }
        // create default instance of Balana
        balana = Balana.getInstance();
    }

    /**
     * Returns a new PDP instance with new XACML policies
     *
     * @return a  PDP instance
     */
    private static PDP getPDPNewInstance(){

        PDPConfig pdpConfig = balana.getPdpConfig();

        // registering new attribute finder. so default PDPConfig is needed to change
        AttributeFinder attributeFinder = pdpConfig.getAttributeFinder();
        List<AttributeFinderModule> finderModules = attributeFinder.getModules();
        finderModules.add(new SampleAttributeFinderModule());
        attributeFinder.setModules(finderModules);

        return new PDP(new PDPConfig(attributeFinder, pdpConfig.getPolicyFinder(), null, true));
    }


    /**
     * Creates DOM representation of the XACML request
     *
     * @param response  XACML request as a String object
     * @return XACML request as a DOM element
     */
    public static Element getXacmlResponse(String response) {

        ByteArrayInputStream inputStream;
        DocumentBuilderFactory dbf;
        Document doc;

        inputStream = new ByteArrayInputStream(response.getBytes());
        dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        try {
            doc = dbf.newDocumentBuilder().parse(inputStream);
        } catch (Exception e) {
            System.err.println("DOM of request element can not be created from String");
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
               System.err.println("Error in closing input stream of XACML response");
            }
        }
        return doc.getDocumentElement();
    }


    public static String createXACMLRequest(String userName, String action, String resource){

        return "<Request xmlns=\"urn:oasis:names:tc:xacml:3.0:core:schema:wd-17\" CombinedDecision=\"false\" ReturnPolicyIdList=\"false\">\n" +
                "<Attributes Category=\"urn:oasis:names:tc:xacml:3.0:attribute-category:action\">\n" +
                "<Attribute AttributeId=\"urn:oasis:names:tc:xacml:1.0:action:action-id\" IncludeInResult=\"false\">\n" +
                "<AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">" + action + "</AttributeValue>\n" +
                "</Attribute>\n" +
                "</Attributes>\n" +
                "<Attributes Category=\"urn:oasis:names:tc:xacml:1.0:subject-category:access-subject\">\n" +
                "<Attribute AttributeId=\"urn:oasis:names:tc:xacml:1.0:subject:subject-id\" IncludeInResult=\"false\">\n" +
                "<AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">"+ userName +"</AttributeValue>\n" +
                "</Attribute>\n" +
                "</Attributes>\n" +
                "<Attributes Category=\"urn:oasis:names:tc:xacml:3.0:attribute-category:resource\">\n" +
                "<Attribute AttributeId=\"urn:oasis:names:tc:xacml:1.0:resource:resource-id\" IncludeInResult=\"true\">\n" +
                "<AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">" + resource + "</AttributeValue>\n" +
                "</Attribute>\n" +
                "</Attributes>\n" +
                "</Request>";

    }

}
