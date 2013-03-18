/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Event;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fraud_000
 */
@Stateless
@WebService
public class EventsManager implements EventsManagerLocal {
    @PersistenceContext(unitName = "MobWebAppComemStarGamePU")
    private EntityManager em;
    
    public Long createEvent(String type) {
        Event event = new Event();
        event.setType(type);
        em.persist(event);
        em.flush();
        return event.getId();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
