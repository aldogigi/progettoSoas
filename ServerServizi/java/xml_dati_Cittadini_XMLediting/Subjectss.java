package xml_dati_Cittadini_XMLediting;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class Subjectss{

	@XmlPath("SubjectMatch/text()")
    private String subjectMatch;
    private String matchIDSubject;
    @XmlPath("SubjectMatch/AttributeValue/text()")
    private String attributeValueSubject;
    @XmlPath("SubjectMatch/AttributeValue/@DataType")
    private String attributeValueSubjectDataTypeString = "http://www.w3.org/2001/XMLSchema#string";
    @XmlPath("SubjectMatch/SubjectAttributeDesignator/text()")
    private String subjectAttributeDesignator;
    private String attributeIdSubject;
    @XmlPath("SubjectMatch/SubjectAttributeDesignator/@DataType")
    private String subjectAttributeDesignatorSubjectDataTypeString = "http://www.w3.org/2001/XMLSchema#string";
    
    @XmlPath("SubjectMatch/@MatchID")
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
    @XmlPath("SubjectMatch/SubjectAttributeDesignator/@AttributeId")
	public String getAttributeIdSubject() {
		return attributeIdSubject;
	}
	public void setAttributeIdSubject(String attributeIdSubject) {
		this.attributeIdSubject = attributeIdSubject;
	}
}
