/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services.REST;

import ch.comem.appli.model.Cours;
import ch.comem.appli.services.CoursesManagerLocal;
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
@Path("cours")
public class CoursFacadeREST  {
    @EJB
    private CoursesManagerLocal coursesManager;

    public CoursFacadeREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Cours entity) {
        this.coursesManager.createCours(entity.getName());
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Cours entity) {
        this.coursesManager.updateCours(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.coursesManager.deleteCours(id);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Cours find(@PathParam("id") Long id) {
        return this.coursesManager.findCourse(id);
    }

    @GET
    @Produces({"application/json", "application/xml"})
    public List<Cours> findAll() {
        return this.coursesManager.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(this.coursesManager.findAll().size());
    }
}
