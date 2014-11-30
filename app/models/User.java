package models;

/**
 * Created by gervasiosantos on 25/11/14.
 */

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "User")
public class User extends Model {

    @Id
    public String username;

    public String email;
    public static Finder<String, User> find = new Finder<String , User>(String.class, User.class);
    public List<Code> codes;

    public User(String username, String email, List<Code> codes){
        this.username = username;
        this.email = email;
        this.codes = codes;
    }

    public void  addCode(String name, File source, Description info){
        List<Ranking> ranks = new LinkedList<Ranking>();
        Code code = new Code(name, source, info, this, ranks);
    }

}
