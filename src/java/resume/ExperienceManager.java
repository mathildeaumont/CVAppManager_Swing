package resume;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathilde on 08/04/14.
 */
public class ExperienceManager {

    private List<Experience> experience;

    public ExperienceManager() {
        this.experience = new ArrayList<Experience>();
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public void addExperience(Experience experience) {
        this.experience.add(experience);
    }

    public void removeExperience(Experience experience) {
        this.experience.remove(experience);
    }
}
