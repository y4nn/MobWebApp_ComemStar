/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
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
    public Classe createClasse(String name) {
        Classe classe = new Classe();
        classe.setName(name);
        em.persist(classe); em.flush();
        return classe;
    }

    

}
