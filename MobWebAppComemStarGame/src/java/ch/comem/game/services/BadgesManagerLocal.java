/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface BadgesManagerLocal {

    public Long createBadge(String name, String description);
    
}
