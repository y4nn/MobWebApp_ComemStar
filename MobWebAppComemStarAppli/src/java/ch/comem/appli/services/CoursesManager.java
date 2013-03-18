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

    

}
