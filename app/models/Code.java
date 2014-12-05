package models;

/**
 * Created by gervasiosantos on 27/11/14.
 */

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "Code")
public class Code extends Model {

    @Id
    public String name;

    public byte[] source;

    public int descriptionID;
    public String submitterID;
    public static Model.Finder<String, Code> findByName = new Model.Finder<String , Code>(String.class, Code.class);

    public Code(byte[] source, String name, int descriptionID, String submitter){
        this.name = name;
        this.descriptionID = descriptionID;
        this.submitterID = submitter;
        this.source = source;
    }

    public Description getDescription(){
        return Description.finder.byId(descriptionID);
    }

    public User getSubmitter(){
        return User.find.byId(submitterID);
    }

    public int nota(){
        List<Ranking> opinioes = Ranking.finder.all();
        int nota = 0;
        for(Ranking r : opinioes){
            if(r.codeID.equals(this.name)){
                if(r.likes_code) nota++;
                else nota--;
            }
        }
        return  nota;
    }

}
