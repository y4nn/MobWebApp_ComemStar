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
    public Badge createBadge(String name, String description, String icone) {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        em.persist(badge);
        em.flush();
        return badge;
    }

    @Override
    public Badge readBadge(Long id) {
        if (id == null) {
            System.out.println("null");
        }
        return this.em.find(Badge.class, id);
    }

    @Override
    public Badge updateBadge(Badge badge) {
        if (badge.getId() != null) {
            Badge badgeAUpdate = this.readBadge(badge.getId());
            if (badge.getDescription() != null) {
                badgeAUpdate.setDescription(badge.getDescription());
            }
            if (badge.getIcone() != null) {
                badgeAUpdate.setIcone(badge.getIcone());
            }

            if (badge.getListePlayers() != null) {
                badgeAUpdate.setListePlayers(badge.getListePlayers());
            }

            if (badge.getName() != null) {
                badgeAUpdate.setName(badge.getName());
            }

            this.em.persist(badgeAUpdate);
            this.em.flush();
            return badgeAUpdate;
        } else {
            return null;
        }
    }

    @Override
    public Badge deleteBadge(Badge badge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
