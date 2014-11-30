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

    /*@Id
    public Integer id;
    */
    @Id
    public String name;

    public File source;
    public int descriptionID;
    public String submitterID;
    public static Model.Finder<String, Code> findByName = new Model.Finder<String , Code>(String.class, Code.class);

    public Code(/*Integer id,*/ String name, File source, int descriptionID, String submitter){
        this.name = name;
        this.source = source;
        this.descriptionID = descriptionID;
        this.submitterID = submitter;
        /*this.id = id;*/
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
