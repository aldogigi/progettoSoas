package xml_dati_Cittadini;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class Resourcess {

	@XmlPath("ResourceMatch/text()")
    private String resourceMatch;
    private String MatchIDResource;
    @XmlPath("ResourceMatch/AttributeValue/text()")
    private String attributeValueResource;
    @XmlPath("ResourceMatch/ResourceAttributeDesignator/text()")
    private String resourceAttributeDesignator;
    private String attributeIdResource;
    
    @XmlPath("ResourceMatch/@MatchID")
	public String getMatchIDResource() {
		return MatchIDResource;
	}
	public void setMatchIDResource(String matchIDResource) {
		MatchIDResource = matchIDResource;
	}
    @XmlPath("ResourceMatch/ResourceAttributeDesignator/@AttributeId")
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
	
}
