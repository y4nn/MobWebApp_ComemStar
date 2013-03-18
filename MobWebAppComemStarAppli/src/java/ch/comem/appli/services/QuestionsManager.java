/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Answer;
import ch.comem.appli.model.Question;
import ch.comem.appli.model.Serie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ziki
 */
@Stateless
public class QuestionsManager implements QuestionsManagerLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Question createQuestion(String question, List<Answer> answers, Serie serie) {
        Question q = new Question();
        q.setQuestion(question);
        q.setReponse(answers);
        q.setSerie(serie);
        em.persist(q);
        em.flush();
        return q;
    }

    

}
