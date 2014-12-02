package models;

/**
 * Created by gervasiosantos on 27/11/14.
 */

import play.db.ebean.Model;
import sun.misc.IOUtils;


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

    @Lob
    public byte[] source;

    public int descriptionID;
    public String submitterID;
    public static Model.Finder<String, Code> findByName = new Model.Finder<String , Code>(String.class, Code.class);

    public Code(String name, File file, int descriptionID, String submitter){
        this.name = name;
        this.source = convertFileToByte(file);
        this.descriptionID = descriptionID;
        this.submitterID = submitter;
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

    public File convertBytesToFIle(String path){
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(path);
            stream.write(source);
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private byte[] convertFileToByte(File file){
        FileInputStream fileInputStream = null;
        source = new byte[(int) file.length()];
        try{
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(source);
            fileInputStream.close();

            for (int i = 0; i < source.length; i++) {
                System.out.print((char)source[i]);
            }

            System.out.println("Done");

        }catch(Exception e){
            System.out.print("Error in reading file.\n");
            e.printStackTrace();
        }
        return source;
    }

}
