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
                + "				<th>Palavras-chave</th>"
                + "				<th>Submetido por</th>"
                + "			</tr>"
                + "		</thead>"
                + "		<tbody>";

        for (Code source : codes) {
            resultado +=
                    "	<tr>"
                            + "	<td>" + source.name + "</td>";
            if (source.info != null)
            {
                resultado += "<td>" + source.info.language + "</td>";
                resultado += "<td>" + source.info.description + "</td>";
                resultado += "<td>" + source.info.keywords + "</td>";
            }
            else {
                resultado += "<td></td>";
            }

            if (source.submitter != null)
            {
                resultado += "<td>" + source.submitter.username + "</td>";
            }
            else
                resultado += "<td></td>";

            resultado += "</tr>";
        }

        resultado += "</tbody></table>";

        return resultado;
    }


}
