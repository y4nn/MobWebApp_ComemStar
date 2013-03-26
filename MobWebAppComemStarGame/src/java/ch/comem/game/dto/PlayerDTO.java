/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Service-Info
 */
@XmlRootElement
public class PlayerDTO implements Serializable {

    private Long id;
    //private String lastName, firstName, email;
    private int nbPoints;
    private List<BadgeDTO> listeBadges = new LinkedList<BadgeDTO>();
    //protected List<EventDTO> listeEvents = new LinkedList<EventDTO>();

    public PlayerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
/*
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
*/
    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public List<BadgeDTO> getListeBadges() {
        return listeBadges;
    }

    public void setListeBadges(List<BadgeDTO> listeBadges) {
        this.listeBadges = listeBadges;
    }
/*
    public List<EventDTO> getListeEvents() {
        return listeEvents;
    }

    public void setListeEvents(List<EventDTO> listeEvents) {
        this.listeEvents = listeEvents;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlayerDTO)) {
            return false;
        }
        PlayerDTO other = (PlayerDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.game.model.Player[ id=" + id + " ]";
    }
}
