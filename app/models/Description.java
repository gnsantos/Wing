package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by gervasiosantos on 27/11/14.
 */

@Entity
@Table(name = "Description")
public class Description extends Model{

    @Id
    public Integer id;

   // public String keywords[] = new String[3];
    public String description;
    public String language;

    public static Model.Finder<Integer, Description> finder = new Model.Finder<Integer, Description>(Integer.class, Description.class);

    public Description(int id, String description, String language/*, String keywords[]*/) {
        this.description = description;
        this.language = language;
        //this.keywords = keywords;
        this.id = new Integer(id);
        System.out.print("Linguagem: " +language+"\nDescription: "+description+"\nId: "+id);
    }
}
