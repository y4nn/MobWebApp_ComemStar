/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services.REST;

import ch.comem.appli.model.Question;
import ch.comem.appli.model.Serie;
import ch.comem.appli.services.QuestionsManagerLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ziki
 */
@Stateless
@Path("questions")
public class QuestionFacadeREST{
    @EJB
    private QuestionsManagerLocal questionsManager;

    public QuestionFacadeREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Question entity) {
        this.questionsManager.createQuestion(entity.getQuestion(), entity.getSerie());
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Question entity) {
        this.questionsManager.updateQuestion(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.questionsManager.deleteQuestion(id);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Question find(@PathParam("id") Long id) {
        return this.questionsManager.findQuestion(id);
    }
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Question> findAll() {
        return this.questionsManager.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(this.questionsManager.findAll().size());
    }
}
