/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Application;
import javax.ejb.Local;

/**
 *
 * @author fraud_000
 */
@Local
public interface ApplicationsManagerLocal {
    public Application createApplication(String name, String description);
}
