/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Serie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface CoursesManagerLocal {

    Cours createCours(String name, List<Serie> series, List<Classe> classes);
    
}
