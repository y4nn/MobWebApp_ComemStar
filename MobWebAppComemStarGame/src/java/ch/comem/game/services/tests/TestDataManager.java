/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.tests;

import ch.comem.game.services.ApplicationsManagerLocal;
import ch.comem.game.services.EventsManagerLocal;
import ch.comem.game.services.PlayersManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author fraud_000
 */
@Stateless
@WebService
public class TestDataManager implements TestDataManagerLocal {

    @EJB
    private ApplicationsManagerLocal applicationsManager;
    @EJB
    private PlayersManagerLocal playersManager;
    @EJB
    private EventsManagerLocal eventsManager;

    public void generateTestData() {
        for (int i = 0; i < 100; i++) {
            this.applicationsManager.createApplication("Application " + i, "Ceci est une application de test no " + i);
            this.playersManager.createPlayer("prenom", "nom", "email");
            this.eventsManager.createEvent("Type"+1);
        }
    }
}
