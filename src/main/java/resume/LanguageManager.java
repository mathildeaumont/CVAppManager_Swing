package resume;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathilde on 08/04/14.
 */
public class LanguageManager {

    private List<Language> language;

    public LanguageManager() {
        language = new ArrayList<Language>();
    }

    public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    public void addLanguage(Language language) {
        this.language.add(language);
    }

    public void removeLanguage(Language language) {
        this.language.remove(language);
    }
}
