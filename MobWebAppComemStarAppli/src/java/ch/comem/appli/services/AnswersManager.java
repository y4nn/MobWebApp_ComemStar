/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Answer;
import ch.comem.appli.model.Question;
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
    public Answer createAnswer(String answer, Boolean isValid, Question question) {
        Answer ans = new Answer();
        ans.setAnswer(answer);
        ans.setIsValid(isValid);
        ans.setQuestion(question);
        em.persist(ans); em.flush();
        return ans;
    }

    @Override
    public Answer findAnswer(Long id) {
        return em.find(Answer.class, id);
    }

    @Override
    public Answer updateAnswer(Answer answerToEdit) {
        Answer answer = this.findAnswer(answerToEdit.getId());
        if(answer != null){
            if(answerToEdit.getAnswer() != null)
                answer.setAnswer(answerToEdit.getAnswer());
            if(answerToEdit.getIsValid() != null)
                answer.setIsValid(answerToEdit.getIsValid());
            if(answerToEdit.getQuestion() != null)
                answer.setQuestion(answerToEdit.getQuestion());
            
            em.persist(answer); em.flush();
        }
        return answer;
    }

    @Override
    public Answer deleteAnswer(Long id) {
        Answer answer = this.findAnswer(id);
        if(answer != null){
            em.remove(answer); em.flush();
        }
        return answer;
    }
    
    

    

}
