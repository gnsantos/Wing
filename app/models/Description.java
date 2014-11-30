package models;

import java.util.List;

/**
 * Created by gervasiosantos on 27/11/14.
 */
public class Description {
    public List<String> keywords;
    public String description;
    public String language;

    public Description(String description, String language, List<String> keywords) {
        this.description = description;
        this.language = language;
        this.keywords = keywords;
    }
}
