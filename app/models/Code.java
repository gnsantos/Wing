package models;

/**
 * Created by gervasiosantos on 27/11/14.
 */

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.List;

@Entity
@Table(name = "Code")
public class Code extends Model {

    @Id
    public int id;

    public String name;
    public File source;
    public Description info;
    public User submitter;
    public List<Ranking> ranks;
    public static Model.Finder<String, Code> findByName = new Model.Finder<String , Code>(String.class, Code.class);

    public Code(String name, File source, Description description, User submitter, List<Ranking> ranks){
        this.name = name;
        this.source = source;
        this.info = description;
        this.submitter = submitter;
        this.ranks = ranks;
    }

    public void addRanking(boolean like, String comment){
        Ranking r = new Ranking(like, comment);
        ranks.add(r);
    }

}
