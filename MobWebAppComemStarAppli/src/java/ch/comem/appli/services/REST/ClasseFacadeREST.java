/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services.REST;

import ch.comem.appli.model.Classe;
import ch.comem.appli.services.ClassesManagerLocal;
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
@Path("classes")
public class ClasseFacadeREST {
    @EJB
    private ClassesManagerLocal classesManager;
    

    public ClasseFacadeREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Classe entity) {
        this.classesManager.createClasse(entity.getName());
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Classe entity) {
        this.classesManager.updateClasse(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.classesManager.deleteClasse(id);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Classe find(@PathParam("id") Long id) {
        return this.classesManager.findClasse(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Classe> findAll() {
        return this.classesManager.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(this.classesManager.findAll().size());
    }
}
