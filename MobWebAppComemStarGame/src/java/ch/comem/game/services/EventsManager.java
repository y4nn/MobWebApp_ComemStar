/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Application;
import ch.comem.game.model.Event;
import ch.comem.game.model.Player;
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

    @Override
    public Event createEvent(String type, Application application, Player player) {
        Event event = new Event();
        event.setType(type);
        event.setApplication(application);
        event.setPlayer(player);
        application.addEvent(event);
        em.persist(event);
        em.flush();
        return event;
    }

    @Override
    public Event readEvent(Long id) {
        if (id == null) {
            System.out.println("null");
        }
        return this.em.find(Event.class, id);
    }

    @Override
    public Event updateEvent(Event event) {
        if (event.getId() != null) {
            Event eventAUpdate = this.readEvent(event.getId());
            if (event.getApplication() != null) {
                eventAUpdate.setApplication(event.getApplication());
            }
            if (event.getPlayer() != null) {
                eventAUpdate.setPlayer(event.getPlayer());
            }

            if (event.getType() != null) {
                eventAUpdate.setType(event.getType());
            }

            this.em.persist(eventAUpdate);
            this.em.flush();
            return eventAUpdate;
        } else {
            return null;
        }
    }

    @Override
    public Event deleteEvent(Event event) {
        if (event.getId() != null) {
            Event eventToDelete = this.readEvent(event.getId());
            this.em.remove(eventToDelete);
            return eventToDelete;
        }
        return null;

    }

    public void persist(Object object) {
        em.persist(object);
    }
}
