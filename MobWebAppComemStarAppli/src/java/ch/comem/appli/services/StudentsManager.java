/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ziki
 */
@Stateless
public class StudentsManager implements StudentsManagerLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Student createStudent(String firstName, String lastName, String mail, String pass, Classe classe) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMail(mail);
        student.setPass(pass);
        
        //if(classe != null)
            student.setClasse(classe);
            classe.addStudent(student);
        
        em.persist(student); em.flush();
        return student;
        
    }
    
    

    @Override
    public Student findStudent(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Student updateStudent(Student studentToEdit) {
        Student student = this.findStudent(studentToEdit.getId());
        
        if(student != null){
            if(studentToEdit.getFirstName() != null)
                student.setFirstName(studentToEdit.getFirstName());
            if(studentToEdit.getLastName() != null)
                student.setLastName(studentToEdit.getLastName());
            if(studentToEdit.getMail() != null)
                student.setMail(studentToEdit.getMail());
            if(studentToEdit.getPass() != null)
                student.setPass(studentToEdit.getPass());
            if(studentToEdit.getClasse() != null)
                student.setClasse(studentToEdit.getClasse());
            
            em.persist(student); em.flush();
        }
        return student;
    }

    @Override
    public Student deleteStudent(Long id) {
        Student studentToDelete = this.findStudent(id);
        if(studentToDelete != null){
            em.remove(studentToDelete);
            em.flush();
        }
        return studentToDelete;
    }
    
    
    
    

    
    

}
