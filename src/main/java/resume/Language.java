package resume;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mathilde on 08/04/14.
 */

public class Language {

    // Nom du langage
    private String name;

    // Niveau
    private String level;

    public Language() {
    }

    public Language(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    @XmlElement
    public void setLevel(String level) {
        this.level = level;
    }
}
