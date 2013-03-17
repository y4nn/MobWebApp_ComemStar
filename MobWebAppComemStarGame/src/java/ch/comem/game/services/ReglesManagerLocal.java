/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface ReglesManagerLocal {

    public Long createRegle(String eventType, String nbPts);

    
}
