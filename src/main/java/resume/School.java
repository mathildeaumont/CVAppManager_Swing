package resume;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mathilde on 08/04/14.
 */

public class School {

    // Nom de l'école
    private String name;

    // Nom du diplôme
    private String diplom;

    // Années d'études
    private String year;

    public School() {
    }

    public School(String name, String diplom, String year) {
        this.name = name;
        this.diplom = diplom;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getDiplom() {
        return diplom;
    }

    @XmlElement
    public void setDiplom(String diplom) {
        this.diplom = diplom;
    }

    public String getYear() {
        return year;
    }

    @XmlElement
    public void setYear(String year) {
        this.year = year;
    }
}
