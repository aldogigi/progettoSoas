package xml_dati_Cittadini;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class XMLEditor{
	
	public XMLEditor() throws Exception{
		

        JAXBContext jc = JAXBContext.newInstance(PolicyCittadini.class);

        PolicyCittadini policyCittadini = new PolicyCittadini();
        
        Subjectss subjects1 = new Subjectss();
        Subjectss subjects2 = new Subjectss();
        
        Resourcess resources1 = new Resourcess();
        
        Actionss action1 = new Actionss();
        
        policyCittadini.setRuleAtt("ruleDaniel");
        policyCittadini.setDescription("Allow Daniel to send a message");
        policyCittadini.setEffectAtt("Permit");
        
        subjects1.setmatchIDSubject("string-equal");
        subjects1.setAttributeValueSubject("Daniel");
        subjects1.setAttributeIdSubject("subject-id");
        
        subjects2.setmatchIDSubject("string-equal");
        subjects2.setAttributeValueSubject("Alex");
        subjects2.setAttributeIdSubject("subject-id");
        
        policyCittadini.setSubjects(subjects1);
        policyCittadini.setSubjects(subjects2);
        System.out.println(policyCittadini.getSubjects());

        resources1.setMatchIDResource("anyURI-equal");
        resources1.setAttributeValueResource("uri:message");
        resources1.setAttributeIdResource("resource=id");
        
        policyCittadini.setResources(resources1);
        System.out.println(policyCittadini.getResources());
        
        action1.setMatchIDAction("string-equal");
        action1.setAttributeValueAction("send");
        action1.setAttributeIdAction("action-id");
        
        policyCittadini.setActions(action1);
        System.out.println(policyCittadini.getActions());
        
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