package xml_dati_Cittadini;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class XMLEditor{
	
	public XMLEditor() throws Exception{

        JAXBContext jc = JAXBContext.newInstance(PolicyCittadini.class);

        PolicyCittadini policyCittadini = new PolicyCittadini();
        
        Ruless rule1 = new Ruless();
        Subjectss subjects1 = new Subjectss();        
        Resourcess resources1 = new Resourcess();
        Actionss action1 = new Actionss();
        
        rule1.setRuleAtt("ruleDaniel");
        rule1.setDescription("Allow Daniel to send a message");
        rule1.setEffectAtt("Permit");
        
        subjects1.setmatchIDSubject("string-equal");
        subjects1.setAttributeValueSubject("Daniel");
        subjects1.setAttributeIdSubject("subject-id");
        
        rule1.setSubjects(subjects1);
        System.out.println(rule1.getSubjects());

        resources1.setMatchIDResource("anyURI-equal");
        resources1.setAttributeValueResource("uri:message");
        resources1.setAttributeIdResource("resource=id");
        
        rule1.setResources(resources1);
        System.out.println(rule1.getResources());
        
        action1.setMatchIDAction("string-equal");
        action1.setAttributeValueAction("send");
        action1.setAttributeIdAction("action-id");
        
        rule1.setActions(action1);
        System.out.println(rule1.getActions());
        
        policyCittadini.setRules(rule1);
        
        File file = new File("src\\main\\resources\\PolicyCittadini.xml");
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(policyCittadini, file);
		
	}
	
	public static void main(String[] args) {
		try {
			new XMLEditor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}