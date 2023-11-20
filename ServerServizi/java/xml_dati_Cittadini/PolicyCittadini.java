package xml_dati_Cittadini;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import jakarta.xml.bind.annotation.XmlElement;
	
public class PolicyCittadini {

	private String policy;
	@XmlPath("Policy/@xmlns")
	private String xmln = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17";
	@XmlPath("Policy/@PolicyId")
	private String policyId = "ServerServiziPolicyCittadini";
	@XmlPath("Policy/@RuleCombiningAlgId")
	private String RuleCombiningAlgId="urn:oasis:names:tc:xacml:3.0:rule-combining-algorithm:deny-overrides";
	@XmlPath("Policy/@Version")
	private String version="1.0";
	@XmlPath("Policy/Target/AnyOf/AnyOf/Match/@MatchId")
	private String matchIdTargetPolicy = "urn:oasis:names:tc:xacml:1.0:function:string-equal";
	@XmlPath("Policy/Target/AnyOf/AnyOf/Match/AttributeValue/text()")
	private String AttributeValuePolicy = "Management of ServerServizi Database";
	@XmlPath("Policy/Target/AnyOf/AnyOf/Match/AttributeValue/@DataType")
	private String DataTypeAttributeValuePolicy = "http://www.w3.org/2001/XMLSchema#string";
	@XmlPath("Policy/Target/AnyOf/AnyOf/Match/AttributeDesignator/@AttributeId")
	private String AttributeIdPolicy = "urn:oasis:names:tc:xacml:1.0:action:action-id";
	@XmlPath("Policy/Target/AnyOf/AnyOf/Match/AttributeDesignator/@Category")
	private String CategoryPolicy = "urn:oasis:names:tc:xacml:3.0:attribute-category:action";
	@XmlPath("Policy/Target/AnyOf/AnyOf/Match/AttributeDesignator/@DataType")
	private String DataTypeAttributeDesignatorPolicy = "http://www.w3.org/2001/XMLSchema#string";
	@XmlPath("Policy/Target/AnyOf/AnyOf/Match/AttributeDesignator/@MustBePresent")
	private String mustBePresent = "true";
	@XmlElement(name = "Policy/Rule", required = true)
    private List<xml_dati_Cittadini.Ruless> rules = new ArrayList<xml_dati_Cittadini.Ruless>();
	
	
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