/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Badge;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ziki
 */
@Stateless
public class BadgesManager implements BadgesManagerLocal {
    @PersistenceContext
    private EntityManager em;

    public BadgesManager() {
    }
    
    @Override
    public Badge createBadge(String name, String description, String icone){
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        em.persist(badge); em.flush();
        return badge;
    }


}
