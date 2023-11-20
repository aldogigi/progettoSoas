package xml_dati_Cittadini;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class Actionss {

	@XmlPath("ActionMatch/text()")
    private String actionMatch;
    private String MatchIDAction;
    @XmlPath("ActionMatch/AttributeValue/text()")
    private String attributeValueAction;
    @XmlPath("ActionMatch/AttributeValue/@DataType")
    private String attributeValueActionDataTypeString = "http://www.w3.org/2001/XMLSchema#string";
    @XmlPath("ActionMatch/ActionAttributeDesignator/text()")
    private String actionAttributeDesignator;
    private String attributeIdAction;
    
    @XmlPath("ActionMatch/@MatchID")
	public String getMatchIDAction() {
		return MatchIDAction;
	}
	public void setMatchIDAction(String matchIDAction) {
		MatchIDAction = matchIDAction;
	}
    @XmlPath("ActionMatch/ActionAttributeDesignator/@AttributeId")
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
	
}
