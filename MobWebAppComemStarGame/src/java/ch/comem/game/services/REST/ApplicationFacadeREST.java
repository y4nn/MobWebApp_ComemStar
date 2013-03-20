/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.REST;

import ch.comem.game.model.Application;
import ch.comem.game.services.ApplicationsManagerLocal;
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
 * @author Service-Info
 */
@Stateless
@Path("ch.comem.game.model.application")
public class ApplicationFacadeREST /*extends AbstractFacade<Application>*/ {
    @PersistenceContext(unitName = "MobWebAppComemStarGamePU")
    private EntityManager em;
    @EJB
    ApplicationsManagerLocal applicationManager;
    public ApplicationFacadeREST() {
        //super(Application.class);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Application entity) {
        //super.create(entity);
        applicationManager.createApplication(entity.getName(), entity.getDescription());
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Application entity) {
        //super.edit(entity);
        applicationManager.updateApplication(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        //super.remove(super.find(id));
        Application appToDelete = applicationManager.findApplication(id);
        applicationManager.deleteApplication(appToDelete);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Application find(@PathParam("id") Long id) {
        //return super.find(id);
        return applicationManager.findApplication(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Application> findAll() {
        //return super.findAll();
        return applicationManager.findAllApplications();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Application> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        //return super.findRange(new int[]{from, to});
        return null;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        //return String.valueOf(super.count());
        return null;
    }


    protected EntityManager getEntityManager() {
        return em;
    }
    
}
