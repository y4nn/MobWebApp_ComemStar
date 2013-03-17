/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import javax.ejb.Local;

/**
 *
 * @author Service-Info
 */
@Local
public interface PlayersManagerLocal {
    public Long createPlayer(String firstName, String lastName, String email);
}
