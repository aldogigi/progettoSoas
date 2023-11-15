package xml_dati_Cittadini;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class XMLEditor{
	
    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(PolicyCittadini.class);

        PolicyCittadini policyCittadini = new PolicyCittadini();
        
        Subjectss subjects1 = new Subjectss();
        Subjectss subjects2 = new Subjectss();
        
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
        System.out.print(policyCittadini.getSubjects());

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