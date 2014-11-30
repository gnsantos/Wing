package controllers;

/**
 * Created by gervasiosantos on 27/11/14.
 */

import java.util.List;

import models.*;


import play.mvc.Result;

import views.html.showquery;

import static play.mvc.Results.ok;


public class Consultas {

    public static Result codes(){
        List<Code> codes = Code.findByName.all();
        return ok(showquery.render(generateHypertext(codes)));
    }


    private static String generateHypertext (List<Code> codes) { // converte a lista de codigos para HTML
        if (codes.size() == 0)
            return "No codes found.";

        String resultado = ""
                + "	<table class = \"table table-striped table-hover\">"
                + "		<thead>"
                + "			<tr>"
                + "				<th>Nome</th>"
                + "				<th>Linguagem</th>"
                + "				<th>Descrição</th>"
                + "				<th>Nota</th>"
                + "				<th>Submetido por</th>"
                + "			</tr>"
                + "		</thead>"
                + "		<tbody>";

        for (Code source : codes) {
            resultado +=
                    "	<tr>"
                            + "	<td>" + source.name + "</td>";
            if (source.descriptionID != 0)
            {
                Description info = Description.finder.byId(source.descriptionID);
                resultado += "<td>" + info.language + "</td>";
                resultado += "<td>" + info.description + "</td>";
                //resultado += "<td>" + info.keywords + "</td>";
            }
            else {
                resultado += "<td></td>";
            }

            resultado += "<td>" + source.nota() + "</td>";

            if (source.submitterID != null)
            {
                resultado += "<td>" + source.submitterID + "</td>";
            }
            else
                resultado += "<td></td>";

            resultado += "</tr>";
        }

        resultado += "</tbody></table>";

        return resultado;
    }


}
