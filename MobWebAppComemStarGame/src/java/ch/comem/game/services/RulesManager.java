/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

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
    public Rule createRules(String eventType, int nbPts) {
        Rule rule = new Rule();
        rule.setEventType(eventType);
        rule.setNbPts(nbPts);
        em.persist(rule);
        em.flush();
        return rule;
    }


    
    
}
