/*
*  Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * hierarchical resource sample
 */
public class XMLBinder {

    private static Balana balana;

    public static void main(String[] args) throws Exception{

        String userName = null;
        String type = null;

        printDescription();

        initBalana();
        
        String request = createXACMLRequest(userName, type);
        PDP pdp = getPDPNewInstance();

        System.out.println("\n======================== XACML Request ====================");
        System.out.println(request);
        System.out.println("===========================================================");

        String response = pdp.evaluate(request);

        System.out.println("\n======================== XACML Response ===================");
        System.out.println(response);
        System.out.println("===========================================================");

        Set<String> permitResources = new HashSet<String>();
        Set<String> denyResources = new HashSet<String>();

        try {
            ResponseCtx responseCtx = ResponseCtx.getInstance(getXacmlResponse(response));
            Set<AbstractResult> results  = responseCtx.getResults();
            for(AbstractResult result : results){
                Set<Attributes> attributesSet = ((Result)result).getAttributes();
                for(Attributes attributes : attributesSet){
                    for(Attribute attribute : attributes.getAttributes()){
                        if(AbstractResult.DECISION_PERMIT == result.getDecision()){
                            permitResources.add(attribute.getValue().encode());
                        } else {
                            denyResources.add(attribute.getValue().encode());
                        }
                    }
                }
            }
        } catch (ParsingException e) {
            e.printStackTrace(); 
        }

        if(permitResources.size() > 0){
            System.out.println("\n is authorized for following resources...\n");
            for(String result : permitResources){
                System.out.print(result + "\t");
            }
            System.out.println("\n");
        } else {
            System.out.println("\n is NOT authorized access any resource..!!!\n");
        }

        System.exit(0);
    }

    private static void initBalana(){

        try{
            // using file based policy repository. so set the policy location as system property
            String policyLocation = (new File(".")).getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "resources";
            System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, policyLocation);
        } catch (IOException e) {
        	e.printStackTrace();
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
        	e.printStackTrace();
            System.err.println("DOM of request element can not be created from String");
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
            	e.printStackTrace();
               System.err.println("Error in closing input stream of XACML response");
            }
        }
        return doc.getDocumentElement();
    }

    public static void printDescription(){

        System.out.println("\nThis mini tool is for test policy created dinamically\n");    

    }


    public static String createXACMLRequest(String userName, String type){

        return "<Request xmlns=\"urn:oasis:names:tc:xacml:1.0:policy\">\n" +
                "<Subject>\n" +
                "<Attribute AttributeId=\"urn:oasis:names:tc:xacml:1.0:subject:subject-id\" DataType=\"http://www.w3.org/2001/XMLSchema#string\">\n" +
                "<AttributeValue>"+ "pntmtn99a44d423e" +"</AttributeValue>\n" +
                "</Attribute>\n" +
                "</Subject>\n" +
                "<Resource>\n" +
                "<Attribute AttributeId=\"urn:oasis:names:tc:xacml:1.0:resource:resource=id_vaccinazione\" DataType=\"http://www.w3.org/2001/XMLSchema#string\">\n" +
                "<AttributeValue>"+ "5" +"</AttributeValue>\n" +
                "</Attribute>\n" +
                "</Resource>\n" +
                "<Action>\n" +
                "<Attribute AttributeId=\"action-id\" DataType=\"http://www.w3.org/2001/XMLSchema#string\">\n" +
                "<AttributeValue>"+ "show" +"</AttributeValue>\n" +
                "</Attribute>\n" +
                "</Action>\n" +
                "</Request>";

    }

}
