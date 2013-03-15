/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.tests;

import ch.comem.game.model.Player;
import ch.comem.game.services.ApplicationsManagerLocal;
import ch.comem.game.services.PlayersManager;
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
    private PlayersManagerLocal playerManager;

    public void generateTestData() {
        for (int i = 0; i < 100; i++) {
            this.applicationsManager.createApplication("Application " + i, "Ceci est une application de test no " + i);
            this.playerManager.createPlayer((long) i, "prenom", "nom", "email", i);
        }
    }
}
