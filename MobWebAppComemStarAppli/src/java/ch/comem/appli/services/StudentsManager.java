/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Serie;
import ch.comem.appli.model.Student;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ziki
 */
@Stateless
public class StudentsManager implements StudentsManagerLocal {

    @EJB
    private ClassesManagerLocal classesManager;
    @PersistenceContext
    private EntityManager em;

    @Override
    public Student createStudent(String firstName, String lastName, String mail, String pass, Long classe_id) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMail(mail);
        student.setPass(pass);
        Classe classe = this.classesManager.findClasse(classe_id);

        student.setClasse(classe);
        if (classe != null) {
            classe.addStudent(student);
        }

        em.persist(student);
        em.flush();

        try {
            ClientConfig cc = new DefaultClientConfig();
            Client client = Client.create(cc);
            WebResource webResource = client.resource("http://localhost:8080/MobWebAppComemStarGame/webresources/players");
            String jsonObject = "{\"nbPoints\":\"0\"}";
            ClientResponse response = webResource.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonObject);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            student.setPlayerID(new Long(output));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public Student findStudent(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Student updateStudent(Student studentToEdit) {
        Student student = this.findStudent(studentToEdit.getId());

        if (student != null) {
            if (studentToEdit.getFirstName() != null) {
                student.setFirstName(studentToEdit.getFirstName());
            }
            if (studentToEdit.getLastName() != null) {
                student.setLastName(studentToEdit.getLastName());
            }
            if (studentToEdit.getMail() != null) {
                student.setMail(studentToEdit.getMail());
            }
            if (studentToEdit.getPass() != null) {
                student.setPass(studentToEdit.getPass());
            }
            if (studentToEdit.getClasse() != null) {
                student.setClasse(studentToEdit.getClasse());
            }

            em.persist(student);
            em.flush();
        }
        return student;
    }

    @Override
    public Student deleteStudent(Long id) {
        Student studentToDelete = this.findStudent(id);
        if (studentToDelete != null) {
            em.remove(studentToDelete);
            em.flush();
        }
        return studentToDelete;
    }

    @Override
    public List<Student> findAll() {
        //List<Student> liste = new LinkedList<Student>();
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        return query.getResultList();

        //return liste;
    }

    @Override
    public Student loginStudent(String mail, String pass) {
        TypedQuery<Student> query = em.createNamedQuery("Student.findByMailAndPass", Student.class).setParameter("mail", mail).setParameter("pass", pass);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
}
