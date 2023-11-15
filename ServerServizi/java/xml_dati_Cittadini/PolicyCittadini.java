package xml_dati_Cittadini;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import jakarta.xml.bind.annotation.*;
	
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
	@XmlPath("Policy/Rule/text()")
    private String rule;
    private String ruleId;
    private String effectAtt;
	@XmlPath("Policy/Rule/Description/text()")
    private String description;
	@XmlPath("Policy/Rule/Target/text()")
    private String target;
	
	@XmlElement(name = "Policy/Rule/Target/Subjects/Subject", required = true)
    private List<xml_dati_Cittadini.Subjectss> subjects = new ArrayList<xml_dati_Cittadini.Subjectss>();
	@XmlElement(name = "Policy/Rule/Target/Resources/Resource", required = true)
    private List<xml_dati_Cittadini.Resourcess> resources = new ArrayList<xml_dati_Cittadini.Resourcess>();
	@XmlElement(name = "Policy/Rule/Target/Actions/Action", required = true)
    private List<xml_dati_Cittadini.Actionss> actions = new ArrayList<xml_dati_Cittadini.Actionss>();
	
	
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	@XmlPath("Policy/Rule/@RuleId")
	public String getRuleAtt() {
		return ruleId;
	}
	
	public void setRuleAtt(String ruleId) {
		this.ruleId = ruleId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public List<Subjectss> getSubjects() {
		return subjects;
	}
	
	public void setSubjects(Subjectss subjectss) {
		this.subjects.add(subjectss);
	}
	
	public List<Resourcess> getResources() {
		return resources;
	}
	
	public void setResources(Resourcess resourcess) {
		this.resources.add(resourcess);
	}
	
	public List<Actionss> getActions() {
		return actions;
	}
	
	public void setActions(Actionss actionss) {
		this.actions.add(actionss);
	}
	
	@XmlPath("Policy/Rule/@Effect")
	public String getEffectAtt() {
		return effectAtt;
	}
	public void setEffectAtt(String effectAtt) {
		this.effectAtt = effectAtt;
	}
	
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}	
}