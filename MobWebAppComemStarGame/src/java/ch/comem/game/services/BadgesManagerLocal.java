/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Badge;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface BadgesManagerLocal {

    public Badge createBadge(String name, String description, String icone);
    
}
