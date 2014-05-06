package resume;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathilde on 08/04/14.
 */
@XmlRootElement
public class ComputerSkillManager {

    private List<ComputerSkill> computerSkill;

    public ComputerSkillManager() {
        this.computerSkill = new ArrayList<ComputerSkill>();
    }

    public List<ComputerSkill> getComputerSkill() {
        return computerSkill;
    }

    public void setComputerSkill(List<ComputerSkill> computerSkill) {
        this.computerSkill = computerSkill;
    }

    public void addComputerSkill(ComputerSkill computerSkill) {
        this.computerSkill.add(computerSkill);
    }

    public void removeComputerSkill(ComputerSkill computerSkill) {
        this.computerSkill.remove(computerSkill);
    }
}
