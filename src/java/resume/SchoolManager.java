package resume;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathilde on 08/04/14.
 */
public class SchoolManager {

    private List<School> school;

    public SchoolManager() {
        school = new ArrayList<School>();
    }

    public List<School> getSchool() {
        return school;
    }

    public void setSchool(List<School> school) {
        this.school = school;
    }

    public void addSchool(School school) {
        this.school.add(school);
    }

    public void removeSchool(School school) {
        this.school.remove(school);
    }
}
