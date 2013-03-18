/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Question;
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
public class SeriesManager implements SeriesManagerLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Serie createSerie(String name, Cours cours, List<Question> questions) {
        Serie serie = new Serie();
        serie.setName(name);
        serie.setCours(cours);
        serie.setQuestion(questions);
        em.persist(serie);
        em.flush();
        return serie;
    }

    @Override
    public Serie findSerie(Long id) {
        return em.find(Serie.class, id);
    }

    @Override
    public Serie updateSerie(Serie serieToEdit) {
        Serie serie = this.findSerie(serieToEdit.getId());
        if(serie != null){
            if(serieToEdit.getName() != null)
                serie.setName(serieToEdit.getName());
            if(serieToEdit.getCours() != null)
                serie.setCours(serieToEdit.getCours());
            if(!serie.getQuestion().isEmpty())
                serie.setQuestion(serieToEdit.getQuestion());
        }
        return serie;
    }

    @Override
    public Serie deleteSerie(Long id) {
        Serie serie = this.findSerie(id);
        if(serie != null){
            em.remove(serie);
            em.flush();
        }
        return serie;
    }
    
    
    
    

    

}
