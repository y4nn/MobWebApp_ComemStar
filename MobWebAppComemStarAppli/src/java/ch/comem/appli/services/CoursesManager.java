/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Serie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ziki
 */
@Stateless
public class CoursesManager implements CoursesManagerLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Cours createCours(String name, List<Serie> series, List<Classe> classes) {
        Cours cours = new Cours();
        cours.setName(name);
        cours.setSerie(series);
        cours.setListeClasses(classes);
        em.persist(cours); em.flush();
        return cours;
    }

    @Override
    public Cours findCourse(Long id) {
        return em.find(Cours.class, id);
    }

    @Override
    public Cours updateCours(Cours coursToEdit) {
        Cours cours = this.findCourse(coursToEdit.getId());
        if(cours != null){
            if(coursToEdit.getName() != null)
                cours.setName(coursToEdit.getName());
            if(!coursToEdit.getSerie().isEmpty())
                cours.setSerie(coursToEdit.getSerie());
            if(!coursToEdit.getListeClasses().isEmpty())
                cours.setListeClasses(coursToEdit.getListeClasses());
            em.persist(cours); em.flush();
        }
        return cours;
    }

    @Override
    public Cours deleteCours(Long id) {
        Cours cours = this.findCourse(id);
        if(cours != null){
            em.remove(cours);
            em.flush();
        }
        return cours;
    }
    
    

    

}
