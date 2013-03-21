/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services.REST;

import ch.comem.appli.model.Answer;
import ch.comem.appli.services.AnswersManagerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("answers")
public class AnswerFacadeREST{
    @EJB
    private AnswersManagerLocal answersManager;

    public AnswerFacadeREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Answer entity) {
        this.answersManager.createAnswer(entity.getAnswer(), entity.getIsValid(), entity.getQuestion());
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Answer entity) {
        this.answersManager.updateAnswer(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.answersManager.deleteAnswer(id);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Answer find(@PathParam("id") Long id) {
        return this.answersManager.findAnswer(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Answer> findAll() {
        return this.answersManager.findAll();
    }
    
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(this.answersManager.findAll().size());
    }
}
