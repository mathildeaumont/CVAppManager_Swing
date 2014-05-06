package resume;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mathilde on 08/04/14.
 */

public class ProfessionalExperience {

    // Nom de l'entreprise
    private String societyName;

    // Années de travail au sein de l'entreprise
    private String year;

    // Tâches effectuées
    private String jobDone;

    public ProfessionalExperience() {
    }

    public ProfessionalExperience(String societyName, String year, String jobDone) {
        this.societyName = societyName;
        this.year = year;
        this.jobDone = jobDone;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getYear() {
        return year;
    }

    @XmlElement
    public void setYear(String year) {
        this.year = year;
    }

    public String getJobDone() {
        return jobDone;
    }

    @XmlElement
    public void setJobDone(String jobDone) {
        this.jobDone = jobDone;
    }
}
