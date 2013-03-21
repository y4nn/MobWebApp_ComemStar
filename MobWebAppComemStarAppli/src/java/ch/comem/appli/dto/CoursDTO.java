/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.dto;

import ch.comem.appli.model.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ziki
 */
public class CoursDTO implements Serializable {
    
    private Long id;
    private String name;
    private List<ClasseDTO> listeClasses = new LinkedList<ClasseDTO>();
    private List<Serie> serie = new LinkedList<Serie>();

    
    public CoursDTO(){
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

    public List<ClasseDTO> getListeClasses() {
        return listeClasses;
    }

    public void setListeClasses(List<ClasseDTO> listeClasses) {
        this.listeClasses = listeClasses;
    }

    public void addClasse(ClasseDTO classe) {
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
        if (!(object instanceof CoursDTO)) {
            return false;
        }
        CoursDTO other = (CoursDTO) object;
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
