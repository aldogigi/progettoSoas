package xml_dati_Cittadini_XMLediting;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class Resourcess {

	@XmlPath("@DataType")
    private String dataType = "http://www.w3.org/2001/XMLSchema#string";
	@XmlPath("text()")
    private String resourcename = "";
    
	public String getResourcename() {
		return resourcename;
	}
	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	
}
