/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Answer;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface AnswersManagerLocal {

    Answer createAnswer(String answer, Boolean isValid);
    
}
