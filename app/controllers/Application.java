package controllers;

import models.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import play.db.ebean.Model;
import views.html.*;

import java.sql.*;

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
        System.out.print(source.getAbsolutePath());

        Description description = new Description(submission.hashCode(), submission.description, submission.language);
        description.save();

        Code code = new Code(/*submitter.hashCode(),*/ source.getName(), source, description.id, submission.submitter);
        code.save();

        return redirect(routes.Application.index());
    }

    public static Result addRanking(){
        RankingSubmission rank = Form.form(RankingSubmission.class).bindFromRequest().get();
        Code code = Code.findByName.byId(rank.codename);
        boolean like = false;
        if(rank.like.equals("like")) like = true;
        Ranking ranking = new Ranking(rank.hashCode(),code.name,like,rank.comment);
        ranking.save();
        return redirect(routes.Application.index());
    }



}
