/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface StudentsManagerLocal {

    Long createStudent(String firstName, String lastName, String mail, String pass);
    
}
