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

    @Override
    public Question findQuestion(Long id) {
        return em.find(Question.class, id);
    }

    @Override
    public Question updateQuestion(Question questionToEdit) {
        Question question = this.findQuestion(questionToEdit.getId());
        if(question != null){
            if(questionToEdit.getQuestion() != null)
                question.setQuestion(questionToEdit.getQuestion());
            if(!questionToEdit.getReponse().isEmpty())
                question.setReponse(questionToEdit.getReponse());
            if(questionToEdit.getSerie() != null)
                question.setSerie(questionToEdit.getSerie());
        }
        return question;
    }

    @Override
    public Question deleteQuestion(Long id) {
        Question question = this.findQuestion(id);
        if(question != null){
            em.remove(question);
        }
        return question;
    }

    
    

}
