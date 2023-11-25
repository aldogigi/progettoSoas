package xml_dati_Cittadini_XMLediting;

import java.io.File;

import javax.xml.bind.*;

public class XMLEditor{
	
	public XMLEditor() throws Exception{

        JAXBContext jc = JAXBContext.newInstance(PolicyCittadini.class);

        PolicyCittadini policyCittadini = new PolicyCittadini();
        
        Ruless rule1 = new Ruless();
        Resourcess resources1 = new Resourcess();
        Resourcess resources2 = new Resourcess();
        Actionss action1 = new Actionss();
        Actionss action2 = new Actionss();
        
        rule1.setRuleAtt("ruleDaniel");
        rule1.setEffectAtt("Permit");
        
        rule1.setAttributeValueSubject("daniel");
        
        resources1.setResourcename("carro");
        resources2.setResourcename("moto");
        
        rule1.setResources(resources1);
        rule1.setResources(resources2);

        System.out.println(rule1.getResources());
        
        action1.setAttributeValueAllOf("show");
        action2.setAttributeValueAllOf("edit");
        
        policyCittadini.setActionss(action1);
        policyCittadini.setActionss(action2);
        
        System.out.println(policyCittadini.getActionss());
        
        policyCittadini.setRules(rule1);
        
        File file = new File("src\\main\\resources\\PolicyCittadiniShowInsert.xml");
        
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