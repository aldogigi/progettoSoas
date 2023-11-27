package xml_dati_Cittadini_XMLediting;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name="Policy")

public class PolicyCittadini {

	@XmlPath("@xmlns")
	private String xmln = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17";
	@XmlPath("@PolicyId")
	private String policyId = "";
	@XmlPath("@RuleCombiningAlgId")
	private String RuleCombiningAlgId="urn:oasis:names:tc:xacml:3.0:rule-combining-algorithm:deny-overrides";
	@XmlPath("@Version")
	private String version="1.0";
	
	@XmlElement(name = "Target/AnyOf/AllOf", required = true)
    private List<xml_dati_Cittadini_XMLediting.Actionss> actionss = new ArrayList<xml_dati_Cittadini_XMLediting.Actionss>();
	
	@XmlElement(name = "Rule", required = true)
    private List<xml_dati_Cittadini_XMLediting.Ruless> rules = new ArrayList<xml_dati_Cittadini_XMLediting.Ruless>();
	
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	
	public List<Actionss> getActionss() {
		return actionss;
	}
	public void setActionss(Actionss actions) {
		actionss.add(actions);
	}
	
	public List<Ruless> getRules() {
		return rules;
	}
	public void setRules(Ruless ruless) {
		this.rules.add(ruless);
	}
}