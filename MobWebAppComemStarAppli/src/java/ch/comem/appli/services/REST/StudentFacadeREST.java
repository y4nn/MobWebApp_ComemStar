/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services.REST;

import ch.comem.appli.model.Student;
import ch.comem.appli.services.StudentsManagerLocal;
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
@Path("ch.comem.appli.model.student")
public class StudentFacadeREST /*extends AbstractFacade<Student>*/ {
    @EJB
    private StudentsManagerLocal studentsManager;
    
    //@PersistenceContext(unitName = "MobWebAppComemStarAppliPU")
    //private EntityManager em;

    
    
    public StudentFacadeREST() {
        //super(Student.class);
    }

    @POST
    //@Override
    @Consumes({"application/xml", "application/json"})
    public void create(Student entity) {
        //super.create(entity);
        this.studentsManager.createStudent(entity.getFirstName(), entity.getLastName(), entity.getMail(), entity.getPass(), null);
    }

    @PUT
    //@Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Student entity) {
        //super.edit(entity);
        this.studentsManager.updateStudent(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        //super.remove(super.find(id));
        this.studentsManager.deleteStudent(id);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Student find(@PathParam("id") Long id) {
        //return super.find(id);
        return this.studentsManager.findStudent(id);
    }

    @GET
    //@Override
    @Produces({"application/xml", "application/json"})
    public List<Student> findAll() {
        //return super.findAll();
        return this.studentsManager.findAll();
    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<Student> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        //return super.findRange(new int[]{from, to});
//        return null;
//    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(this.studentsManager.findAll().size());
    }

//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
    
}
