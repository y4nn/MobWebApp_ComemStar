/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.REST;

import ch.comem.game.model.Event;
import ch.comem.game.model.Rule;
import ch.comem.game.services.EventsManagerLocal;
import ch.comem.game.services.PlayersManagerLocal;
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
@Path("events")
public class EventFacadeREST /*extends AbstractFacade<Event> */{
    @PersistenceContext(unitName = "MobWebAppComemStarGamePU")
    private EntityManager em;
    @EJB
    EventsManagerLocal eventsManager;
    @EJB
    PlayersManagerLocal playersManager;

    public EventFacadeREST() {
        //super(Event.class);
    }

    @POST
    @Consumes({"application/json", "application/xml" })
    public void create(Event entity) {
        //super.create(entity);
        System.out.println("coucou");
        System.out.println(entity.getType());
        System.out.println(entity.getApplication().getId());
        System.out.println(entity.getPlayer().getId());
        
        Event event = eventsManager.createEvent(entity.getType(), entity.getApplication(), entity.getPlayer());
        List<Rule> rules = event.getApplication().getRules();
        for(Rule rule : rules){
            if(event.getType().equals(rule.getEventType())){
                playersManager.associateBadge(event.getPlayer(), rule.getBadge());
                break;
            }
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Event entity) {
        //super.edit(entity);
        eventsManager.updateEvent(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        //super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Event find(@PathParam("id") Long id) {
        //return super.find(id);
        return null;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Event> findAll() {
        //return super.findAll();
        return null;
    }

    /*@GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Event> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }*/

    protected EntityManager getEntityManager() {
        return em;
    }
    
}
