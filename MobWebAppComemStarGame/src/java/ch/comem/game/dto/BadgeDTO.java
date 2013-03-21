/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.game.dto;

import ch.comem.game.model.Rule;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ziki
 */
@XmlRootElement
public class BadgeDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String icone;
    private List<PlayerDTO> listePlayers = new LinkedList<PlayerDTO>();
    private RuleDTO ruleDTO;

    public RuleDTO getRule() {
        return ruleDTO;
    }

    public void setRule(RuleDTO ruleDTO) {
        this.ruleDTO = ruleDTO;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PlayerDTO> getListePlayers() {
        return listePlayers;
    }

    public void setListePlayers(List<PlayerDTO> listePlayers) {
        this.listePlayers = listePlayers;
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
        if (!(object instanceof BadgeDTO)) {
            return false;
        }
        BadgeDTO other = (BadgeDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.model.Badge[ id=" + id + " ]";
    }
}
