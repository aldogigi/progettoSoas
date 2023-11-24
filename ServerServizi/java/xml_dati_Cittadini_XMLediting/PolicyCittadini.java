package xml_dati_Cittadini_XMLediting;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name="Policy")

public class PolicyCittadini {

	private String policy;
	@XmlPath("@xmlns")
	private String xmln = "urn:oasis:names:tc:xacml:1.0:policy";
	@XmlPath("@PolicyId")
	private String policyId = "ServerServiziPolicyCittadini";
	@XmlPath("@RuleCombiningAlgId")
	private String RuleCombiningAlgId="urn:oasis:names:tc:xacml:3.0:rule-combining-algorithm:deny-overrides";
	@XmlPath("@Version")
	private String version="1.0";
	@XmlPath("Target/Subjects/AnySubject/text()")
	private String anySubject = "";
	@XmlPath("Target/Resources/AnyResource/text()")
	private String anyResource = "";
	@XmlPath("Target/Actions/AnyAction/text()")
	private String anyAction = "";
	@XmlElement(name = "Rule", required = true)
    private List<xml_dati_Cittadini_XMLediting.Ruless> rules = new ArrayList<xml_dati_Cittadini_XMLediting.Ruless>();
	
	
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}	
	
	public List<Ruless> getRules() {
		return rules;
	}
	public void setRules(Ruless ruless) {
		this.rules.add(ruless);
	}
}