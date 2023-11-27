package xml_dati_Cittadini_XMLediting;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
* @author Gianluca Fontana 21452A
* @author Alex Rabuffetti 20290A
*/


public class Ruless {

    private String ruleId;
    private String effectAtt;
  
	@XmlPath("Target/AnyOf/AllOf/Match/@MatchId")
	private String subjectMatchId = "urn:oasis:names:tc:xacml:1.0:function:string-equal";
	@XmlPath("Target/AnyOf/AllOf/Match/AttributeValue/text()")
	private String attributeValueSubject = "";
	@XmlPath("Target/AnyOf/AllOf/Match/AttributeValue/@DataType")
	private String dataTypeAttributeValueSubject = "http://www.w3.org/2001/XMLSchema#string";
	@XmlPath("Target/AnyOf/AllOf/Match/AttributeDesignator/@AttributeId")
	private String attributeIdAttributeDesignatorSubject = "http://wso2.org/attribute/roleNames";
	@XmlPath("Target/AnyOf/AllOf/Match/AttributeDesignator/@Category")
	private String categoryAttributeDesignatorSubject = "urn:oasis:names:tc:xacml:1.0:subject-category:access-subject";
	@XmlPath("Target/AnyOf/AllOf/Match/AttributeDesignator/@DataType")
	private String dataTypeAttributeDesignatorSubject = "http://www.w3.org/2001/XMLSchema#string";
	@XmlPath("Target/AnyOf/AllOf/Match/AttributeDesignator/@MustBePresent")
	private String presentAttributeDesignatorSubject = "true";
	@XmlPath("Condition/Apply/@FunctionId")
	private String conditionApplyFunctionId = "urn:oasis:names:tc:xacml:1.0:function:string-at-least-one-member-of";
	@XmlPath("Condition/Apply/Apply/@FunctionId")
	private String conditionApplyApplyFunctionId = "urn:oasis:names:tc:xacml:1.0:function:string-bag";
	
	@XmlPath("Condition/Apply/AttributeDesignator/@AttributeId")
	private String conditionApply1AttributeDesignatorAttributeId = "urn:oasis:names:tc:xacml:1.0:resource:resource-id";
	@XmlPath("Condition/Apply/AttributeDesignator/@Category")
	private String conditionApply1AttributeDesignatorCategory = "urn:oasis:names:tc:xacml:3.0:attribute-category:resource";
	@XmlPath("Condition/Apply/AttributeDesignator/@DataType")
	private String conditionApply1AttributeDesignatorDataType = "http://www.w3.org/2001/XMLSchema#string";
	@XmlPath("Condition/Apply/AttributeDesignator/@MustBePresent")
	private String conditionApply1AttributeDesignatorMustBePresent = "true";
	
	@XmlElement(name = "Condition/Apply/Apply/AttributeValue", required = true)
    private List<xml_dati_Cittadini_XMLediting.Resourcess> resourcess = new ArrayList<xml_dati_Cittadini_XMLediting.Resourcess>();
	
	@XmlPath("@RuleId")
	public String getRuleAtt() {
		return ruleId;
	}
	
	public void setRuleAtt(String ruleId) {
		this.ruleId = ruleId;
	}
	
	@XmlPath("@Effect")
	public String getEffectAtt() {
		return effectAtt;
	}
	public void setEffectAtt(String effectAtt) {
		this.effectAtt = effectAtt;
	}
	
	public String getAttributeValueSubject() {
		return attributeValueSubject;
	}
	public void setAttributeValueSubject(String attributeValueSubject) {
		this.attributeValueSubject = attributeValueSubject;
	}
	
	public List<Resourcess> getResources() {
		return resourcess;
	}
	
	public void setResources(Resourcess resources) {
		this.resourcess.add(resources);
	}
	
	
}
