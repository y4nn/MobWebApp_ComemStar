/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.tests;

import ch.comem.game.model.Application;
import ch.comem.game.model.Badge;
import ch.comem.game.services.ApplicationsManagerLocal;
import ch.comem.game.services.BadgesManagerLocal;
import ch.comem.game.services.EventsManagerLocal;
import ch.comem.game.services.PlayersManagerLocal;
import ch.comem.game.services.RulesManagerLocal;
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
    @EJB
    private RulesManagerLocal rulesManager;
    @EJB
    private BadgesManagerLocal badgesManager;

    public void generateTestData() {
        for (int i = 0; i < 100; i++) {
            Application app = this.applicationsManager.createApplication("Application " + i, "Ceci est une application de test no " + i);
            System.out.println(app.getId());
            this.playersManager.createPlayer("prenom", "nom", "email");
            this.eventsManager.createEvent("Type"+i);
            this.rulesManager.createRules("Type"+i, i);
            Badge badge = this.badgesManager.createBadge("Badge"+i, "Ceci est le badge no "+i, "icone"+i);
            System.out.println(badge.getId());
        }
    }
}
