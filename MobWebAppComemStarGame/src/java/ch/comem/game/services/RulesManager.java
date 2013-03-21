/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Application;
import ch.comem.game.model.Rule;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author admin
 */
@Stateless
@WebService
public class RulesManager implements RulesManagerLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Rule createRule(String eventType, int nbPtsMin, int nbPtsMax, Application application) {
        Rule rule = new Rule();
        rule.setEventType(eventType);
        rule.setNbPtsMax(nbPtsMax);
        rule.setNbPtsMin(nbPtsMin);
        rule.setApplication(application);
        application.addRule(rule);
        em.persist(rule);
        em.flush();
        return rule;
    }

    public Rule findRule(Long id){
        return em.find(Rule.class, id);
    }

    public Rule updateRule(Rule ruleModel){
        Rule ruleToUpdate = em.find(Rule.class, ruleModel.getId());
        if(ruleModel.getEventType() != null){
            ruleToUpdate.setEventType(ruleModel.getEventType());
        }
        if(ruleModel.getApplication()!= null){
            ruleToUpdate.setApplication(ruleModel.getApplication());
        }
        if(ruleModel.getApplication()!= null){
            ruleToUpdate.setApplication(ruleModel.getApplication());
        }     
        ruleToUpdate.setNbPtsMax(ruleModel.getNbPtsMax());
        ruleToUpdate.setNbPtsMin(ruleModel.getNbPtsMin());
        return ruleToUpdate;
    }

    public void deleteRule(Rule rule){
        em.remove(rule);
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    
}
