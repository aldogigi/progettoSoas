package xml_dati_Cittadini;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import jakarta.xml.bind.annotation.XmlElement;

public class Ruless {

    private String ruleId;
    private String effectAtt;
	@XmlPath("Description/text()")
    private String description;
	@XmlPath("Target/text()")
    private String target;
	
	@XmlElement(name = "Target/Subjects/Subject", required = true)
    private List<xml_dati_Cittadini.Subjectss> subjects = new ArrayList<xml_dati_Cittadini.Subjectss>();
	@XmlElement(name = "Target/Resources/Resource", required = true)
    private List<xml_dati_Cittadini.Resourcess> resources = new ArrayList<xml_dati_Cittadini.Resourcess>();
	@XmlElement(name = "Target/Actions/Action", required = true)
    private List<xml_dati_Cittadini.Actionss> actions = new ArrayList<xml_dati_Cittadini.Actionss>();
	

	@XmlPath("@RuleId")
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
	
	@XmlPath("@Effect")
	public String getEffectAtt() {
		return effectAtt;
	}
	public void setEffectAtt(String effectAtt) {
		this.effectAtt = effectAtt;
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
	
	
}
