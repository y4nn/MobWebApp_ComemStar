/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.dto;

import ch.comem.game.model.Application;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sandra
 */
@XmlRootElement
public class RuleDTO implements Serializable {

    private Long id;
    protected ApplicationDTO application;
    private int nbPts;
    private String eventType;

    public void setApplication(ApplicationDTO application) {
        this.application = application;
    }

    public ApplicationDTO getApplication() {
        return this.application;
    }

    public int getNbPts() {
        return nbPts;
    }

    public void setNbPts(int nbPts) {
        this.nbPts = nbPts;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleDTO)) {
            return false;
        }
        RuleDTO other = (RuleDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.game.model.Regle[ id=" + id + " ]";
    }
}
