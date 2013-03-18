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
    @PersistenceContext(unitName = "MobWebAppComemStarGamePU")
    private EntityManager em;

    @Override
    public Player createPlayer(String firstName, String lastName, String email) {
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);
        player.setNbPoints(0);
        em.persist(player);
        em.flush();
        return player;
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
    
}
