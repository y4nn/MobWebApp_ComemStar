/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ziki
 */
@Entity
@XmlRootElement
@NamedQueries({
@NamedQuery(name="Question.findAll", query="select object(o) from Question o")//,
//@NamedQuery(name="Customer.findById",query="select object(cust) from Customer cust where cust.custid = :custid"),
//@NamedQuery(name="Customer.findByEmail",query="select object(cust) from Customer cust where cust.email = :email and cust.password = :password")
})
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @ManyToOne private Serie serie;
    @OneToMany(mappedBy="question") private List<Answer> reponse = new LinkedList<Answer>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @XmlTransient
    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public List<Answer> getReponse() {
        return reponse;
    }

    public void setReponse(List<Answer> reponse) {
        this.reponse = reponse;
    }
    
    public void addAnswer(Answer answer) {
        this.reponse.add(answer);
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.appli.model.Question[ id=" + id + " ]";
    }
    
}
