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
public class ClasseDTO implements Serializable {
    private Long id;
    private String name;
    private List<StudentDTO> student = new LinkedList<StudentDTO>();
    private List<CoursDTO> listeCours = new LinkedList<CoursDTO>();
    
    public ClasseDTO(){
        //this.student = new LinkedList<Student>();
        //this.listeCours = new LinkedList<Cours>();
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

    public List<StudentDTO> getStudent() {
        return student;
    }

    public void setStudent(List<StudentDTO> student) {
        this.student = student;
    }
    
    public void addStudent(StudentDTO student){
        this.student.add(student);
    }

    public List<CoursDTO> getListeCours() {
        return listeCours;
    }

    public void setListeCours(List<CoursDTO> listeCours) {
        this.listeCours = listeCours;
    }
    
    public void addCours(CoursDTO cours){
        this.listeCours.add(cours);
        //cours.addClasse(this);//
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
        if (!(object instanceof Classe)) {
            return false;
        }
        ClasseDTO other = (ClasseDTO) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.appli.model.Classe[ id=" + id + " ]";
    }
    
}
