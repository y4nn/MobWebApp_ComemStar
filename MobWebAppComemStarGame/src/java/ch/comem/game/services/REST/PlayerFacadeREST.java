/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.REST;

import ch.comem.game.dto.BadgeDTO;
import ch.comem.game.dto.EventDTO;
import ch.comem.game.dto.PlayerDTO;
import ch.comem.game.model.Badge;
import ch.comem.game.model.Event;
import ch.comem.game.model.Player;
import ch.comem.game.services.PlayersManagerLocal;
import java.util.LinkedList;
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
 * @author Service-Info
 */
@Stateless
@Path("ch.comem.game.model.player")
public class PlayerFacadeREST {

    @EJB
    private PlayersManagerLocal manager;

    public PlayerFacadeREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Player player) {
        this.manager.createPlayer(player.getFirstName(), player.getLastName(), player.getEmail(), player.getListeBadges(), player.getListeEvents());
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Player entity) {
        // super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        //super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public PlayerDTO find(@PathParam("id") Long id) {
        Player player = manager.readPlayer(id);
        PlayerDTO playerDTO = null;
        if (player != null) {
            playerDTO = new PlayerDTO();
            playerDTO.setId(id);
            playerDTO.setFirstName(player.getFirstName());
            playerDTO.setLastName(player.getLastName());
            playerDTO.setEmail(player.getEmail());
            List<Badge> listeB = player.getListeBadges();
            List<BadgeDTO> listeBDTO = new LinkedList<BadgeDTO>();
            if (listeB != null) {
                for (Badge b : listeB) {
                    BadgeDTO badgeDTO = new BadgeDTO();
                    badgeDTO.setId(b.getId());
                    badgeDTO.setDescription(b.getDescription());
                    badgeDTO.setIcone(b.getIcone());
                    badgeDTO.setName(b.getName());
                    listeBDTO.add(badgeDTO);
                }
            }
            playerDTO.setListeBadges(listeBDTO);
            List<Event> listeE = player.getListeEvents();
            List<EventDTO> listeEDTO = new LinkedList<EventDTO>();
            if (listeE != null) {
                for (Event e : listeE) {
                    EventDTO eventDTO = new EventDTO();
                    eventDTO.setId(e.getId());
                    eventDTO.setType(e.getType());
                    listeEDTO.add(eventDTO);
                }
            }
            playerDTO.setListeEvents(listeEDTO);
        }
        return playerDTO;
    }
//    @GET
//    @Produces({"application/xml", "application/json"})
//    public List<Player> findAll() {
//        return super.findAll();
//    }
//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<Player> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String countREST() {
//        return String.valueOf(super.count());
//    }
//
//    protected EntityManager getEntityManager() {
//        return em;
//    }
}
