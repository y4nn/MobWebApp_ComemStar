/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Cours;
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
    public Cours createCours(String name) {
        Cours cours = new Cours();
        cours.setName(name);
        em.persist(cours); em.flush();
        return cours;
    }

    

}
