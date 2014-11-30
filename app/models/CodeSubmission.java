package models;

import java.io.File;
import java.util.List;

/**
 * Created by gervasiosantos on 28/11/14.
 */
public class CodeSubmission {
    public String name;
    public File file;
    public String language;
    public String description;
    public String[] kw = new String[3];
    public String submitter;
}
