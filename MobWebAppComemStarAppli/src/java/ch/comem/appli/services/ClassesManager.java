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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ziki
 */
@Stateless
public class ClassesManager implements ClassesManagerLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Classe createClasse(String name, List<Student> listStudents, List<Cours> listCours) {
        Classe classe = new Classe();
        classe.setName(name);
        classe.setStudent(listStudents);
        classe.setListeCours(listCours);
        
        em.persist(classe); em.flush();
        return classe;
    }

    @Override
    public Classe findClasse(Long id) {
        return em.find(Classe.class, id);
    }

    @Override
    public Classe updateClasse(Classe classeToEdit) {
        Classe classe = this.findClasse(classeToEdit.getId());
        
        if(classe !=  null){
            if(classeToEdit.getName() != null)
                classe.setName(classeToEdit.getName());
            if(!classeToEdit.getStudent().isEmpty())
                classe.setStudent(classeToEdit.getStudent());
            if(!classeToEdit.getListeCours().isEmpty())
                classe.setListeCours(classeToEdit.getListeCours());
            em.persist(classe); em.flush();
        }
        return classe;
    }

    @Override
    public Classe deleteClasse(Long id) {
        Classe classe = this.findClasse(id);
        if(classe != null){
            for (Student student : classe.getStudent()) {
                student.setClasse(null);
            }
            
            for (Cours cours : classe.getListeCours()) {
                cours.setListeClasses(null);
            }
            em.remove(classe);
            em.flush();
        }
        return classe;
    }
    
    
    

    

}
