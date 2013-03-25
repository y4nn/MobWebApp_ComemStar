/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.REST;

import ch.comem.game.model.Rule;
import ch.comem.game.services.RulesManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Service-Info
 */
@Stateless
@Path("rules")
public class RuleFacadeREST {
    @EJB
    private RulesManagerLocal manager;

    public RuleFacadeREST() {
       
    }

    @POST
   
    @Consumes({"application/xml", "application/json"})
    public void create(Rule rule) {
        this.manager.createRule(rule.getEventType(),rule.getApplication());
    }

    @PUT
    
    @Consumes({"application/xml", "application/json"})
    public void edit(Rule entity) {
        //super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
//        super.remove(super.find(id));
    }

//    @GET
//    @Path("{id}")
//    @Produces({"application/xml", "application/json"})
//    public Rule find(@PathParam("id") Long id) {
//        return super.find(id);
//    }

//    @GET
//    @Produces({"application/xml", "application/json"})
//    public List<Rule> findAll() {
//        return super.findAll();
//    }
//
//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<Rule> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String countREST() {
//        return String.valueOf(super.count());
//    }
//    
}
