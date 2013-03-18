/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Question;
import ch.comem.appli.model.Serie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface SeriesManagerLocal {

    Serie createSerie(String name, Cours cours, List<Question> questions);

    Serie findSerie(Long id);

    Serie updateSerie(Serie serieToEdit);

    Serie deleteSerie(Long id);
    
}
