package xml_dati_Cittadini;

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
	@XmlPath("Policy/Rule/text()")
    private String rule;
    private String ruleId;
    private String effectAtt;
	@XmlPath("Policy/Rule/Description/text()")
    private String description;
	@XmlPath("Policy/Rule/Target/text()")
    private String target;
	
	
	@XmlPath("Policy/Rule/Target/Subjects/text()")	
    private String subjects;
	@XmlPath("Policy/Rule/Target/Subjects/Subject/text()")
    private String subject;
	@XmlPath("Policy/Rule/Target/Subjects/Subject/SubjectMatch/text()")
    private String subjectMatch;
    private String matchIDSubject;
    @XmlPath("Policy/Rule/Target/Subjects/Subject/SubjectMatch/AttributeValue/text()")
    private String attributeValueSubject;
    @XmlPath("Policy/Rule/Target/Subjects/Subject/SubjectMatch/SubjectAttributeDesignator/text()")
    private String subjectAttributeDesignator;
    private String attributeIdSubject;
    
	@XmlPath("Policy/Rule/Target/Subjects/Subject/text()")
    private String subjects2 = "Zu ciccio u capraro";
//   	@XmlPath("Policy/Rule/Target/Subjects/Subject/text()")
//    private String subject2;
//   	@XmlPath("Policy/Rule/Target/Subjects/Subject/SubjectMatch/text()")
//    private String subjectMatch2;
//    private String matchIDSubject2;
//    @XmlPath("Policy/Rule/Target/Subjects/Subject/SubjectMatch/AttributeValue/text()")
//    private String attributeValueSubject2;
//    @XmlPath("Policy/Rule/Target/Subjects/Subject/SubjectMatch/SubjectAttributeDesignator/text()")
//    private String subjectAttributeDesignator2 = "1";
//    private String attributeIdSubject2;
    
	@XmlPath("Policy/Rule/Target/Resources/text()")
    private String resources;
	@XmlPath("Policy/Rule/Target/Resources/Resource/text()")
    private String resource;
	@XmlPath("Policy/Rule/Target/Resources/Resource/ResourceMatch/text()")
    private String resourceMatch;
    private String MatchIDResource;
    @XmlPath("Policy/Rule/Target/Resources/Resource/ResourceMatch/AttributeValue/text()")
    private String attributeValueResource;
    @XmlPath("Policy/Rule/Target/Resources/Resource/ResourceMatch/ResourceAttributeDesignator/text()")
    private String resourceAttributeDesignator;
    private String attributeIdResource;
    
    @XmlPath("Policy/Rule/Target/Actions/text()")
    private String actions;
	@XmlPath("Policy/Rule/Target/Actions/Action/text()")
    private String action;
	@XmlPath("Policy/Rule/Target/Actions/Action/ActionMatch/text()")
    private String actionMatch;
    private String MatchIDAction;
    @XmlPath("Policy/Rule/Target/Actions/Action/ActionMatch/AttributeValue/text()")
    private String attributeValueAction;
    @XmlPath("Policy/Rule/Target/Actions/Action/ActionMatch/ActionAttributeDesignator/text()")
    private String actionAttributeDesignator;
    private String attributeIdAction;
    
    
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
	@XmlPath("Policy/Rule/@Effect")
	public String getEffectAtt() {
		return effectAtt;
	}
	public void setEffectAtt(String effectAtt) {
		this.effectAtt = effectAtt;
	}
	@XmlPath("Policy/Rule/Target/Subjects/Subject/SubjectMatch/@MatchID")
	public String getmatchIDSubject() {
		return matchIDSubject;
	}
	public void setmatchIDSubject(String matchIDSubject) {
		this.matchIDSubject = matchIDSubject;
	}
	
	public String getAttributeValueSubject() {
		return attributeValueSubject;
	}
	public void setAttributeValueSubject(String AttributeValueSubject) {
		this.attributeValueSubject = AttributeValueSubject;
	}
    @XmlPath("Policy/Rule/Target/Subjects/Subject/SubjectMatch/SubjectAttributeDesignator/@AttributeId")
	public String getAttributeIdSubject() {
		return attributeIdSubject;
	}
	public void setAttributeIdSubject(String attributeIdSubject) {
		this.attributeIdSubject = attributeIdSubject;
	}
	@XmlPath("Policy/Rule/Target/Resources/Resource/ResourceMatch/@MatchID")
	public String getMatchIDResource() {
		return MatchIDResource;
	}
	public void setMatchIDResource(String matchIDResource) {
		MatchIDResource = matchIDResource;
	}
    @XmlPath("Policy/Rule/Target/Resources/Resource/ResourceMatch/ResourceAttributeDesignator/@AttributeId")
	public String getAttributeIdResource() {
		return attributeIdResource;
	}
	public void setAttributeIdResource(String attributeIdResource) {
		this.attributeIdResource = attributeIdResource;
	}
	public String getAttributeValueResource() {
		return attributeValueResource;
	}
	public void setAttributeValueResource(String attributeValueResource) {
		this.attributeValueResource = attributeValueResource;
	}
	@XmlPath("Policy/Rule/Target/Actions/Action/ActionMatch/@MatchID")
	public String getMatchIDAction() {
		return MatchIDAction;
	}
	public void setMatchIDAction(String matchIDAction) {
		MatchIDAction = matchIDAction;
	}
    @XmlPath("Policy/Rule/Target/Actions/Action/ActionMatch/ActionAttributeDesignator/@AttributeId")
	public String getAttributeIdAction() {
		return attributeIdAction;
	}
	public void setAttributeIdAction(String attributeIdAction) {
		this.attributeIdAction = attributeIdAction;
	}
	
	public String getAttributeValueAction() {
		return attributeValueAction;
	}
	public void setAttributeValueAction(String AttributeValueAction) {
		this.attributeValueAction = AttributeValueAction;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}

}