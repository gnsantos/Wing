package controllers;

/**
 * Created by gervasiosantos on 27/11/14.
 */

import java.util.List;

import models.Code;

import play.mvc.Result;

import views.html.showquery;

import play.mvc.Controller;


public class Consultas extends Controller {

    public static Result getAllCodes(){
        List<Code> codes = Code.findByName.all();
        return ok(showquery.render(codes));
    }

   /* public static Result searchCodesByDescription(){

    }*/

}
