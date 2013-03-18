/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Rule;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface RulesManagerLocal {
    
    public Rule createRules(String eventType, int nbPts);
    
}
