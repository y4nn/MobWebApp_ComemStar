/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Badge;
import ch.comem.game.model.Event;
import ch.comem.game.model.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Service-Info
 */
@Stateless
public class PlayersManager implements PlayersManagerLocal {

    @PersistenceContext(unitName = "MobWebAppComemStarGamePU")
    private EntityManager em;

    @Override
    public Player createPlayer(String firstName, String lastName, String email, int nbPoints, List<Badge> listeBadges, List<Event> listeEvents) {
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);
        player.setNbPoints(0);
        player.setListeBadges(listeBadges);
        player.setListeEvents(listeEvents);
        em.persist(player);
        em.flush();
        return player;
    }

    @Override
    public Player readPlayer(Long id) {
        if (id == null) {
            System.out.println("null");
        }
        return this.em.find(Player.class, id);
    }

    @Override
    public Player updatePlayer(Player player) {
        if (player.getId() != null) {
            Player playerAUpdate = this.readPlayer(player.getId());
            if (player.getFirstName() != null) {
                playerAUpdate.setFirstName(player.getFirstName());
            }
            if (player.getLastName() != null) {
                playerAUpdate.setLastName(player.getLastName());
            }

            if (player.getEmail() != null) {
                playerAUpdate.setEmail(player.getEmail());
            }

            if (player.getListeBadges() != null) {
                playerAUpdate.setListeBadges(player.getListeBadges());
            }
            if (player.getListeEvents() != null) {
                playerAUpdate.setListeEvents(player.getListeEvents());
            }
            this.em.persist(playerAUpdate);
            this.em.flush();
            return playerAUpdate;
        } else {
            return null;
        }
    }

    @Override
    public Player deletePlayer(Player player) {
        if (player.getId() != null) {
            Player playerToDelete = this.readPlayer(player.getId());
            this.em.remove(playerToDelete);
            return playerToDelete;
        }
        return null;
    }

    @Override
    public void associateBadge(Player playerAAssocie, Badge badgeAAssocie) {
        Player player = em.find(Player.class, playerAAssocie.getId());
        Badge badge = em.find(Badge.class, badgeAAssocie.getId());
        player.getListeBadges().add(badge);
        badge.getListePlayers().add(player);
    }
    
    @Override
    public void associateEvent(Long id_player, Long id_event) {
        Player player = em.find(Player.class, id_player);
        Event event = em.find(Event.class, id_event);
        player.getListeEvents().add(event);
        event.setPlayer(player);
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
