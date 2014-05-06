package resume;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathilde on 08/04/14.
 */
@XmlRootElement
public class ProfessionalExperienceManager {

    private List<ProfessionalExperience> professionalExperience;

    public ProfessionalExperienceManager() {
        professionalExperience = new ArrayList<ProfessionalExperience>();
    }

    public List<ProfessionalExperience> getProfessionalExperience() {
        return professionalExperience;
    }

    public void setProfessionalExperience(List<ProfessionalExperience> professionalExperience) {
        this.professionalExperience = professionalExperience;
    }

    public void addProfessionnalExperience(ProfessionalExperience professionnalExperience) {
        this.professionalExperience.add(professionnalExperience);
    }

    public void removeProfessionnalExperience(ProfessionalExperience professionnalExperience) {
        this.professionalExperience.remove(professionnalExperience);
    }
}
