/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Player;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Service-Info
 */
@Stateless
public class PlayersManager implements PlayersManagerLocal {

    @PersistenceContext
    private EntityManager em;

    public PlayersManager() {
    }

    @Override
    public Long createPlayer(Long id, String firstName, String lastName, String email, int nbPoints) {
        Player player = new Player();
        player.setId(id);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);
        player.setNbPoints(nbPoints);
        em.persist(player);
        em.flush();
        return player.getId();
    }
}
