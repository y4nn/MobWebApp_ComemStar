/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.dto;

import ch.comem.game.model.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fraud_000
 */
@XmlRootElement
public class ApplicationDTO implements Serializable {

    private Long id;
    protected List<RuleDTO> rules = new LinkedList<RuleDTO>();
    protected List<EventDTO> events = new LinkedList<EventDTO>();

    public List<RuleDTO> getRules() {
        return this.rules;
    }

    public RuleDTO addRule(RuleDTO rule) {
        getRules().add(rule);
        return rule;
    }

    public List<EventDTO> getEvents() {
        return this.events;
    }

    public EventDTO addEvent(EventDTO event) {
        this.getEvents().add(event);
        return event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof ApplicationDTO)) {
            return false;
        }
        ApplicationDTO other = (ApplicationDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.game.model.Application[ id=" + id + " ]";
    }
}
