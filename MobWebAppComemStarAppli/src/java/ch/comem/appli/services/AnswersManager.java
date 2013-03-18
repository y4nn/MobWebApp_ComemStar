/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Answer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ziki
 */
@Stateless
public class AnswersManager implements AnswersManagerLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Answer createAnswer(String answer, Boolean isValid) {
        Answer ans = new Answer();
        ans.setAnswer(answer);
        ans.setIsValid(isValid);
        em.persist(ans); em.flush();
        return ans;
    }

    

}
