/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@NamedQuery(name="Cours.findAll", query="select object(o) from Cours o")//,
//@NamedQuery(name="Customer.findById",query="select object(cust) from Customer cust where cust.custid = :custid"),
//@NamedQuery(name="Customer.findByEmail",query="select object(cust) from Customer cust where cust.email = :email and cust.password = :password")
})
public class Cours implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy="listeCours") private List<Classe> listeClasses = new LinkedList<Classe>();
    @OneToMany(mappedBy="cours") private List<Serie> serie = new LinkedList<Serie>();

    
    public Cours(){
        //this.listeClasses = new LinkedList<Classes>();
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

    @XmlTransient
    public List<Classe> getListeClasses() {
        return listeClasses;
    }

    public void setListeClasses(List<Classe> listeClasses) {
        this.listeClasses = listeClasses;
    }

    public void addClasse(Classe classe) {
        this.listeClasses.add(classe);
        //classe.addCours(this);//
        
    }
    
    public List<Serie> getSerie() {
        return serie;
    }

    public void setSerie(List<Serie> serie) {
        this.serie = serie;
    }
    
    public void addSerie(Serie serie) {
        this.serie.add(serie);
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
        if (!(object instanceof Cours)) {
            return false;
        }
        Cours other = (Cours) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.appli.model.Cours[ id=" + id + " ]";
    }
    
}
