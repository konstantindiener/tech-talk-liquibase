package biz.cosee.talks.loaning;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Language {

    public static final Language EN = new Language("en", "English");

    @Id
    private String languageCode;

    private String description;

    protected Language() {
    }

    public Language(String languageCode, String description) {
        this.languageCode = languageCode;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
