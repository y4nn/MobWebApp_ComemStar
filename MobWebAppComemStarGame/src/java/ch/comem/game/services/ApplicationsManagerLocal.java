/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Application;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fraud_000
 */
@Local
public interface ApplicationsManagerLocal {
    public Application createApplication(String name, String description);
    public Application findApplication(Long id);
    public List<Application> findAllApplications();
    public Application updateApplication(Application applicationModel);
    public void deleteApplication(Application application);
}
