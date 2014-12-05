package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;

import sun.misc.IOUtils;

import views.html.*;

import java.io.File;
import java.io.IOException;

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

    public static Result addCode(){
        Form<CodeSubmission> codeSubmissionForm = Form.form(CodeSubmission.class).bindFromRequest();

        String submitterUsername = codeSubmissionForm.field("submitter").value();
        String password = codeSubmissionForm.field("password").value();
        String filename = codeSubmissionForm.field("filename").value();
        String language = codeSubmissionForm.field("language").value();
        String description = codeSubmissionForm.field("description").value();
        File submittedFile;

        User submittingUser = User.find.byId(submitterUsername);

        if(submittingUser == null){
            return ok(index.render("AddCodeError"));
        } else if(!password.equals(submittingUser.getPassword())){
            return ok(index.render("WrongPassword"));
        }

        Description info = new Description(codeSubmissionForm.hashCode(), description, language);

        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart filePart = body.getFile("file");

        if(filePart != null){
            submittedFile = filePart.getFile();
        } else{
            return ok(index.render("AddCommentError"));
        }

        Code code = new Code(submittedFile, filename, info.id, submitterUsername);
        info.save();
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
