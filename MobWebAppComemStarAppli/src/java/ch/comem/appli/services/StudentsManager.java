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
        student.setClasse(classe);
        em.persist(student); em.flush();
        return student;
        
    }

    

}
