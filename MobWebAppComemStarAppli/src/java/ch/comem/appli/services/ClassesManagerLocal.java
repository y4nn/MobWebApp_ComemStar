/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface ClassesManagerLocal {

    Classe createClasse(String name);
    
}
