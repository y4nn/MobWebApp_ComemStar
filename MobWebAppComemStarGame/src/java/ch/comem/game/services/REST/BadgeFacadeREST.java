/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.REST;

import ch.comem.game.dto.BadgeDTO;
import ch.comem.game.model.Badge;
import ch.comem.game.services.BadgesManagerLocal;
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
@Path("badges")
public class BadgeFacadeREST {

    @EJB
    private BadgesManagerLocal manager;

    public BadgeFacadeREST() {
        //super(Badge.class);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Badge badge) {
        this.manager.createBadge(badge.getName(), badge.getDescription(), badge.getIcone());
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Badge entity) {
//        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
//        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json", "application/xml"})
    public BadgeDTO find(@PathParam("id") Long id) {
        Badge badge = this.manager.readBadge(id);
        BadgeDTO badgeDTO = null;
        if (badge != null) {
            badgeDTO = new BadgeDTO();
            badgeDTO.setId(id);
            badgeDTO.setName(badge.getName());
            badgeDTO.setDescription(badge.getDescription());
            badgeDTO.setIcone(badge.getIcone());
        }
        return badgeDTO;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<BadgeDTO> findAll() {
        List<Badge> listeB = this.manager.readAllBadges();
        List<BadgeDTO> listeBDTO = null;
        if (listeB != null) {
            listeBDTO = new LinkedList<BadgeDTO>();
            for (Badge b : listeB) {
                BadgeDTO badgeDTO = new BadgeDTO();
                badgeDTO.setId(b.getId());
                badgeDTO.setDescription(b.getDescription());
                badgeDTO.setIcone(b.getIcone());
                badgeDTO.setName(b.getName());
                listeBDTO.add(badgeDTO);
            }
        }
        return listeBDTO;
    }
//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<Badge> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
//    
}
