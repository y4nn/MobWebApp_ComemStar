/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Serie;
import ch.comem.appli.model.Student;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ziki
 */
@Stateless
public class ClassesManager implements ClassesManagerLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Classe createClasse(String name) {
        Classe classe = new Classe();
        classe.setName(name);
        
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
                cours.getListeClasses().remove(classe);
            }
            em.remove(classe);
            em.flush();
        }
        return classe;
    }

    @Override
    public Classe addStudent(Classe classe, Student student) {
        classe.addStudent(student);
        student.setClasse(classe);
        em.flush();
        return classe;
    }

    @Override
    public Classe addCours(Classe classe, Cours cours) {
        classe.addCours(cours);
        cours.addClasse(classe);
        em.flush();
        return classe;
    }

    @Override
    public List<Classe> findAll() {
        TypedQuery<Classe> query = em.createNamedQuery("Classe.findAll", Classe.class);
        return query.getResultList();
    }
    
    
    
    
    
    
    

    

}
