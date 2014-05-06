package resume;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mathilde on 08/04/14.
 */

public class Experience {

    // Nom de l'expérience
    private String name;

    // Description de l'expérience
    private String description;

    // Années de l'expérience
    private String year;

    public Experience() {
    }

    public Experience(String name, String description, String year) {
        this.name = name;
        this.description = description;
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    @XmlElement
    public void setYear(String year) {
        this.year = year;
    }
}
