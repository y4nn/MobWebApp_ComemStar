/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Regle;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sandra
 */
@Stateless
@WebService
public class ReglesManager implements ReglesManagerLocal {
    @PersistenceContext(unitName = "MobWebAppComemStarGamePU")
    private EntityManager em;
    
    
    @Override
    public Long createRegle(String eventType, String nbPts) {
        Regle regle = new Regle();
        regle.setEventType(eventType);
        regle.setNbPts(nbPts);
        em.persist(regle);
        em.flush();
        return regle.getId();
    }
    
    public void persist(Object object) {
    em.persist(object);
    
    }


    

}
