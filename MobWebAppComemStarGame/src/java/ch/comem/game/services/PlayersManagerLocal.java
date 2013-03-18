/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Badge;
import ch.comem.game.model.Event;
import ch.comem.game.model.Player;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Service-Info
 */
@Local
public interface PlayersManagerLocal {

    public Player createPlayer(String firstName, String lastName, String email, List<Badge> listeBadges, List<Event> listeEvents);

    public Player readPlayer(Long id);

    public Player updatePlayer(Player player);

    public Player deletePlayer(Player player);
}
