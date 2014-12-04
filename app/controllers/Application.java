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
import java.lang.Thread;

import play.api.libs.json.*;


public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Insert users and code."));
    }

    public static Result signUp() {
        return ok(signup.render());
    }

    public static Result submissions() {
        return ok(submission.render());
    }

    public static Result comments() {
        return ok(comments.render());
    }


    public static Result addUser() {
        User user = Form.form(User.class).bindFromRequest().get();

        User checkIfExists = User.find.byId(user.username);

        if(checkIfExists != null){
            return ok(index.render("AddUserError"));
        }

        user.save();
        return redirect(routes.Application.index());
    }

    public static Result addCode() {
        CodeSubmission submission = Form.form(CodeSubmission.class).bindFromRequest().get();

        User submitter = User.find.byId(submission.submitter);

        if (submitter == null){
            return ok(index.render("AddCodeError"));
        }
        else if(!submission.password.equals(submitter.getPassword())){
            System.err.println(submitter.getPassword() + " + " + submission.password);
            return ok(index.render("WrongPassword"));
        }

        File source = submission.file;

        System.out.print(source.getName());
        System.out.print(source.getAbsolutePath());

        Description description = new Description(submission.hashCode(), submission.description, submission.language);
        description.save();

        Code code = new Code(source.getName(), source, description.id, submission.submitter);
        code.save();

        return redirect(routes.Application.index());
    }

    public static Result addRanking(){
        RankingSubmission rank = Form.form(RankingSubmission.class).bindFromRequest().get();
        Code code = Code.findByName.byId(rank.codename);
        if(code == null){
            return ok(index.render("AddCommentError"));
        }
        boolean like = false;
        if(rank.like.equals("like")) like = true;
        Ranking ranking = new Ranking(rank.hashCode(),code.name,like,rank.comment);
        ranking.save();
        return redirect(routes.Application.index());
    }



}
