package resume; /**
 * Created by Mathilde on 01/04/14.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import resume.Resume;


@Controller
@RequestMapping("/resume")
public class XMLController {

    private static ResumeManager resumes;

    public XMLController() {
        resumes = new ResumeManager();

        // Ecoles
        SchoolManager schoolManager = new SchoolManager();
        School school = new School("Université de Rouen", "Licence informatique", "2012-2013");
        schoolManager.addSchool(school);

        // Experiences professionnelles
        ProfessionalExperienceManager professionalExperienceManager = new ProfessionalExperienceManager();
        ProfessionalExperience professionalExperience = new ProfessionalExperience("Progimax", "2013", "Développeur Web");
        professionalExperienceManager.addProfessionnalExperience(professionalExperience);

        // Langues
        LanguageManager languageManager = new LanguageManager();
        Language anglais = new Language("Anglais", "Moyen");
        languageManager.addLanguage(anglais);
        Language allemand = new Language("Allemand", "Moyen");
        languageManager.addLanguage(allemand);

        // Expériences
        ExperienceManager experienceManager = new ExperienceManager();
        Experience exp1 = new Experience("Site internet de l'AMAP du Roumois", "Projet annuel de master 1 GIL", "2013-2014");
        experienceManager.addExperience(exp1);
        Experience exp2 = new Experience("Sudoku", "Projet annuel de licence 3 informatique - Java", "2012-2013");
        experienceManager.addExperience(exp2);

        // Compétences informatiques
        ComputerSkillManager computerSkillManager = new ComputerSkillManager();
        ComputerSkill java = new ComputerSkill("Java", "+++");
        computerSkillManager.addComputerSkill(java);
        ComputerSkill c = new ComputerSkill("C", "+");
        computerSkillManager.addComputerSkill(c);


        Resume resume = new Resume("Aumont", "Mathilde", "Acquérir de l'expérience",
                schoolManager, professionalExperienceManager, languageManager, experienceManager, computerSkillManager);
        resumes.addResume(resume);
        Resume resume2 = new Resume("Aumont", "Mathilde", "Acquérir de l'expérience",
                schoolManager, professionalExperienceManager, languageManager, experienceManager, computerSkillManager);
        resumes.addResume(resume2);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResumeManager getResumes() {
        return resumes;
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody Resume getResume(@PathVariable int id) {
        return resumes.getResume(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody void addResume(@RequestBody Resume resume) {
        resumes.addResume(resume);
    }

}