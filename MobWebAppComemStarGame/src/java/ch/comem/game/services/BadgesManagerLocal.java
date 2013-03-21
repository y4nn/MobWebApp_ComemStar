/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.services;

import ch.comem.game.model.Badge;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ziki
 */
@Local
public interface BadgesManagerLocal {

    public Badge createBadge(String name, String description, String icone);

    public Badge readBadge(Long id);
    
    public List<Badge> readAllBadges();

    public Badge updateBadge(Badge badge);

    public Badge deleteBadge(Badge badge);
}
