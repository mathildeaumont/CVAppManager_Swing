package resume;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mathilde on 08/04/14.
 */

public class ComputerSkill {

    // Nom de la compétence
    private String name;

    // Niveau de la compétence
    private String level;

    public ComputerSkill() {
    }

    public ComputerSkill(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    @XmlElement
    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }


}
