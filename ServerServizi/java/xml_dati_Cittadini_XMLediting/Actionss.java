package xml_dati_Cittadini_XMLediting;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class Actionss {


	@XmlPath("Match/@MatchId")
	private String MatchIdAllOf = "urn:oasis:names:tc:xacml:1.0:function:string-equal";
	@XmlPath("Match/AttributeValue/text()")
	private String attributeValueAllOf = "";
	@XmlPath("Match/AttributeValue/@DataType")
	private String DataTypeAllOf = "http://www.w3.org/2001/XMLSchema#string";
	@XmlPath("Match/AttributeDesignator/@AttributeId")
	private String AttributeIdAttributeDesignator = "urn:oasis:names:tc:xacml:1.0:action:action-id";
	@XmlPath("Match/AttributeDesignator/@Category")
	private String CategoryAttributeDesignator = "urn:oasis:names:tc:xacml:3.0:attribute-category:action";
	@XmlPath("Match/AttributeDesignator/@DataType")
	private String DataTypeAttributeDesignator = "http://www.w3.org/2001/XMLSchema#string";
	@XmlPath("Match/AttributeDesignator/@MustBePresent")
	private String MustBePresentAttributeDesignator = "true";
	
	public String getAttributeValueAllOf() {
		return attributeValueAllOf;
	}
	public void setAttributeValueAllOf(String attributeValueAllOf) {
		this.attributeValueAllOf = attributeValueAllOf;
	}
    
}
