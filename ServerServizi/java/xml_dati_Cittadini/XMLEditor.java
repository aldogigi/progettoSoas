package xml_dati_Cittadini;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class XMLEditor {

	public XMLEditor(){
		
		
		
	}
    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(PolicyCittadini.class);

        PolicyCittadini policyCittadini = new PolicyCittadini();
        policyCittadini.setRuleAtt("ruleDaniel");
        policyCittadini.setDescription("Allow Daniel to send a message");
        policyCittadini.setEffectAtt("Permit");
        
        policyCittadini.setmatchIDSubject("string-equal");
        policyCittadini.setAttributeValueSubject("Daniel");
        policyCittadini.setAttributeIdSubject("subject-id");
        
        policyCittadini.setMatchIDResource("anyURI-equal");
        policyCittadini.setAttributeValueResource("uri:message");
        policyCittadini.setAttributeIdResource("resource=id");
        
        policyCittadini.setMatchIDAction("string-equal");
        policyCittadini.setAttributeValueAction("send");
        policyCittadini.setAttributeIdAction("action-id");

        File file = new File("src\\main\\resources\\PolicyCittadini.xml");
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(policyCittadini, file);
    }
}