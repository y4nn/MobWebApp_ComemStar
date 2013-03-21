/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.dto;

import ch.comem.appli.model.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ziki
 */
@XmlRootElement
public class SerieDTO implements Serializable {
    
    private Long id;
    private String name;
    private CoursDTO cours;
    private List<QuestionDTO> question = new LinkedList<QuestionDTO>();

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
    
    public CoursDTO getCours() {
        return cours;
    }

    public void setCours(CoursDTO cours) {
        this.cours = cours;
    }

    public List<QuestionDTO> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionDTO> question) {
        this.question = question;
    }
    
    public void addQuestion(QuestionDTO question) {
        this.question.add(question);
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
        if (!(object instanceof Serie)) {
            return false;
        }
        SerieDTO other = (SerieDTO) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.appli.model.Serie[ id=" + id + " ]";
    }
    
}
