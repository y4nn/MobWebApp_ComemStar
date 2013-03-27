/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services.tests;

import ch.comem.game.model.Application;
import ch.comem.game.model.Badge;
import ch.comem.game.model.Player;
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
            Application app = this.applicationsManager.createApplication("comemstar", "application qui vous rend plus intelligent");
            Application app2 = this.applicationsManager.createApplication("comemstar", "application qui vous rend plus intelligent");
        for (int i = 1; i <= 5; i++) {
//            Long id_player = this.playersManager.createPlayer("prenom", "nom", "email",0, null, null);
//            Badge badge = this.badgesManager.createBadge("Badge" + i, "Ceci est le badge no " + i, "icone" + i);
//            this.eventsManager.createEvent("Type" + i, app, id_player);
//            this.playersManager.associateBadge(this.playersManager.readPlayer(new Long(i)), badge);
//            this.rulesManager.createRule("Serie " + i + " terminee avec un score compris entre 90% et 99%", app2, badge);       
        }
        Badge badge1 = this.badgesManager.createBadge("Badge1", "Ceci est le badge no 1", "icone1");
        Badge badge2 = this.badgesManager.createBadge("Badge2", "Ceci est le badge no 2", "icone2");
        Badge badge3 = this.badgesManager.createBadge("Badge3", "Ceci est le badge no 3", "icone3");
        Badge badge4 = this.badgesManager.createBadge("Badge4", "Ceci est le badge no 4", "icone4");
        this.rulesManager.createRule("Serie 1 terminee avec un score inférieur à 50%", app2, badge1);
        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 50% et 70%", app2, badge2);
        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 70% et 90%", app2, badge3);
        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 90% et 99%", app2, badge4);
    }
}
