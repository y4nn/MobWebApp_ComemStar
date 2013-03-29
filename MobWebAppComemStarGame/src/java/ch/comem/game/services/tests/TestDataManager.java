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

        Badge badge11 = this.badgesManager.createBadge("Connaissances Générales", "Serie 'Connaissances Générales' terminée avec un score compris entre 50% et 70%", "medal_bronze");
        Badge badge21 = this.badgesManager.createBadge("Connaissances Générales", "Serie 'Connaissances Générales' terminée avec un score compris entre 70% et 90%", "medal_silver");
        Badge badge31 = this.badgesManager.createBadge("Connaissances Générales", "Serie 'Connaissances Générales' terminée avec un score compris entre 90% et 99%", "medal_gold");
        Badge badge41 = this.badgesManager.createBadge("Connaissances Générales", "Serie 'Connaissances Générales' terminée avec un score parfait", "diamond");
        Badge badge51 = this.badgesManager.createBadge("Mobile WEB Service", "Cours 'Mobile WEB Service' terminé avec un score parfait", "cup");
        
        Badge badge12 = this.badgesManager.createBadge("REST", "Serie 'REST' terminée avec un score compris entre 50% et 70%", "medal_bronze");
        Badge badge22 = this.badgesManager.createBadge("REST", "Serie 'REST' terminée avec un score compris entre 70% et 90%", "medal_silver");
        Badge badge32 = this.badgesManager.createBadge("REST", "Serie 'REST' terminée avec un score compris entre 90% et 99%", "medal_gold");
        Badge badge42 = this.badgesManager.createBadge("REST", "Serie 'REST' terminée avec un score parfait", "diamond");

        Badge badge13 = this.badgesManager.createBadge("Gamification", "Serie 'Gamification' terminée avec un score compris entre 50% et 70%", "medal_bronze");
        Badge badge23 = this.badgesManager.createBadge("Gamification", "Serie 'Gamification' terminée avec un score compris entre 70% et 90%", "medal_silver");
        Badge badge33 = this.badgesManager.createBadge("Gamification", "Serie 'Gamification' terminée avec un score compris entre 90% et 99%", "medal_gold");
        Badge badge43 = this.badgesManager.createBadge("Gamification", "Serie 'Gamification' terminée avec un score parfait", "diamond");
        
        Badge badge14 = this.badgesManager.createBadge("Layers", "Serie 'Layers' terminée avec un score compris entre 50% et 70%", "medal_bronze");
        Badge badge24 = this.badgesManager.createBadge("Layers", "Serie 'Layers' terminée avec un score compris entre 70% et 90%", "medal_silver");
        Badge badge34 = this.badgesManager.createBadge("Layers", "Serie 'Layers' terminée avec un score compris entre 90% et 99%", "medal_gold");
        Badge badge44 = this.badgesManager.createBadge("Layers", "Serie 'Layers' terminée avec un score parfait", "diamond");
        Badge badge54 = this.badgesManager.createBadge("IHM", "Cours 'IHM' terminé avec un score parfait", "cup");



        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 50% et 70%", app, badge11);
        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 70% et 90%", app, badge21);
        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 90% et 99%", app, badge31);
        this.rulesManager.createRule("Serie 1 terminee avec un score parfait", app, badge41);
        this.rulesManager.createRule("a obtenu tous les badges en diamand du cours 1", app, badge51);
        
        this.rulesManager.createRule("Serie 2 terminee avec un score compris entre 50% et 70%", app, badge12);
        this.rulesManager.createRule("Serie 2 terminee avec un score compris entre 70% et 90%", app, badge22);
        this.rulesManager.createRule("Serie 2 terminee avec un score compris entre 90% et 99%", app, badge32);
        this.rulesManager.createRule("Serie 2 terminee avec un score parfait", app, badge42);
        
        this.rulesManager.createRule("Serie 3 terminee avec un score compris entre 50% et 70%", app, badge13);
        this.rulesManager.createRule("Serie 3 terminee avec un score compris entre 70% et 90%", app, badge23);
        this.rulesManager.createRule("Serie 3 terminee avec un score compris entre 90% et 99%", app, badge33);
        this.rulesManager.createRule("Serie 3 terminee avec un score parfait", app, badge43);
        
        this.rulesManager.createRule("Serie 4 terminee avec un score compris entre 50% et 70%", app, badge14);
        this.rulesManager.createRule("Serie 4 terminee avec un score compris entre 70% et 90%", app, badge24);
        this.rulesManager.createRule("Serie 4 terminee avec un score compris entre 90% et 99%", app, badge34);
        this.rulesManager.createRule("Serie 4 terminee avec un score parfait", app, badge44);
        this.rulesManager.createRule("a obtenu tous les badges en diamand du cours 1", app, badge54);
        
        
        
//        Badge badge5 = this.badgesManager.createBadge("Layers", "Ceci est le badge diamand de la serie Layers", "diamond");
//        Badge badge6 = this.badgesManager.createBadge("Actions", "Ceci est le badge diamand de la serie Actions", "diamond");
//        Badge badge7 = this.badgesManager.createBadge("Recursivité", "Ceci est le badge diamand de la serie Recursivité", "diamond");
//        Badge badge8 = this.badgesManager.createBadge("IHM", "Ceci est la coupe si vous avez tous les diamands du cours IHM", "diamond");
//        this.rulesManager.createRule("Serie 1 terminee avec un score inférieur à 50%", app, badge5);
//        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 50% et 70%", app, badge6);
//        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 70% et 90%", app, badge7);
//        this.rulesManager.createRule("Serie 1 terminee avec un score compris entre 90% et 99%", app, badge);
//        this.rulesManager.createRule("Serie 3 terminee avec un score parfait", app, badge5);
//        this.rulesManager.createRule("Serie 4 terminee avec un score parfait", app, badge6);
//        this.rulesManager.createRule("Serie 5 terminee avec un score parfait", app, badge7);
//        this.rulesManager.createRule("a obtenu tous les badges en diamand du cours 2", app, badge8);
    }
}
