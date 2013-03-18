/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Student;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface StudentsManagerLocal {

    Student createStudent(String firstName, String lastName, String mail, String pass, Classe classe);
    
}
