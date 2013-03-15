/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Application;
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
public class ApplicationsManager implements ApplicationsManagerLocal {
    @PersistenceContext(unitName = "MobWebAppComemStarGamePU")
    private EntityManager em;

   @Override
   public Long createApplication(String name, String description){
       Application application = new Application();
       application.setName(name);
       application.setDescription(description);
       em.persist(application);
       em.flush();
       return application.getId();
   } 

    public void persist(Object object) {
        em.persist(object);
    }

}
