/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.tests;

import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface TestsManagerLocal {

    void populateDB();

    void createClassesCours();

    void deleteCours(Long idCours);
    
    
}
