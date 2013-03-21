/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Student;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface ClassesManagerLocal {

    Classe createClasse(String name);

    Classe findClasse(Long id);

    Classe updateClasse(Classe classeToEdit);

    Classe deleteClasse(Long id);

    Classe addStudent(Classe classe, Student student);

    Classe addCours(Classe classe, Cours cours);

    List<Classe> findAll();
    
}
