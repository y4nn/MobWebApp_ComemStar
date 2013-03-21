/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Serie;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ziki
 */
@Stateless
public class CoursesManager implements CoursesManagerLocal {
    @EJB
    private ClassesManagerLocal classesManager;
    
    @PersistenceContext
    private EntityManager em;
    
    

    @Override
    public Cours createCours(String name) {
        Cours cours = new Cours();
        cours.setName(name);
        //cours.setSerie(series);
        //cours.setListeClasses(classes);
        em.persist(cours); em.flush();
        return cours;
    }
    
    @Override
    public Cours addClasse(Cours cours, Classe classe) {
        cours.addClasse(classe);
        classe.addCours(cours);
        em.persist(cours);
        em.flush();
        return cours;
    }
    
    @Override
    public Cours addSerie(Cours cours, Serie serie) {
        cours.addSerie(serie);
        serie.setCours(cours);
        em.persist(cours);
        em.flush();
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
            em.persist(cours); em.flush();
        }
        return cours;
    }

    @Override
    public Cours deleteCours(Long id) {
        Cours cours = this.findCourse(id);
        if(cours != null){
            for (Classe classe : cours.getListeClasses()) {
                classe.getListeCours().remove(cours);
            }
            for (Serie serie : cours.getSerie()) {
                serie.setCours(null);
            }
            em.remove(cours);
            em.flush();
        }
        return cours;
    }

    @Override
    public List<Cours> findAll() {
        TypedQuery<Cours> query = em.createNamedQuery("Cours.findAll", Cours.class);
        return query.getResultList();
    }

    

    
    
    

    

}
