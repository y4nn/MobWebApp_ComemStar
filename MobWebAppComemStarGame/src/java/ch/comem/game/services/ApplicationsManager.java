/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Application;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
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
   public Application createApplication(String name, String description){
       Application application = new Application();
       application.setName(name);
       application.setDescription(description);
       em.persist(application);
       em.flush();
       return application;
   } 
   
   @Override
   public Application findApplication(Long id){
       return em.find(Application.class, id);
   }
   
    @Override
   public List<Application> findAllApplications(){
       return em.createNamedQuery("findApplication").getResultList();
   }
   
   @Override
   public Application updateApplication(Application applicationModel){
       Application applicationToUpdate = em.find(Application.class, applicationModel.getId());
       if(applicationModel.getDescription() != null){
           applicationToUpdate.setDescription(applicationModel.getDescription());
       }
       if(applicationModel.getName() != null){
           applicationToUpdate.setName(applicationModel.getName());
       }
       return applicationToUpdate;
   }
   
   public void deleteApplication(Application application){
       em.remove(application);
   }

    public void persist(Object object) {
        em.persist(object);
    }

}
