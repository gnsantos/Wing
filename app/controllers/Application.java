package controllers;

import models.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import play.db.ebean.Model;
import views.html.*;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import play.api.libs.json.*;


public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Insert users and code."));
    }

    public static Result addUser() {
        User user = Form.form(User.class).bindFromRequest().get();
        try {
            user.save();
        }catch (Exception e){
            return ok(index.render("Invalid username."));
        }
        return redirect(routes.Application.index());
    }

    public static Result addCode() {
        CodeSubmission submission = Form.form(CodeSubmission.class).bindFromRequest().get();

        User submitter = User.find.byId(submission.submitter);

        File source = submission.file;

        System.out.print(source.getName());

        List<String> kws = new LinkedList<String>();
        kws.add(submission.kw[0]); kws.add(submission.kw[1]); kws.add(submission.kw[2]);

        List<Ranking> ranks = new LinkedList<Ranking>();

        Description description = new Description(submission.description, submission.language, kws);

        Code code = new Code(source.getName(), source, description, submitter, ranks);

        code.save();

        return redirect(routes.Application.index());
    }



}
