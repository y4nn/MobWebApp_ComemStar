/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Answer;
import ch.comem.appli.model.Question;
import ch.comem.appli.model.Serie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface QuestionsManagerLocal {

    Question createQuestion(String question, Serie serie);

    Question findQuestion(Long id);

    Question updateQuestion(Question questionToEdit);

    Question deleteQuestion(Long id);

    Question addReponse(Question question, Answer answer);
    
}
