/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Question;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface QuestionsManagerLocal {

    Question createQuestion(String question);
    
}
