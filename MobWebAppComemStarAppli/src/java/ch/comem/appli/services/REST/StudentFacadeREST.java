/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services.REST;

import ch.comem.appli.dto.ClasseDTO;
import ch.comem.appli.dto.CoursDTO;
import ch.comem.appli.dto.SerieDTO;
import ch.comem.appli.dto.StudentDTO;
import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Serie;
import ch.comem.appli.model.Student;
import ch.comem.appli.services.StudentsManagerLocal;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ziki
 */
@Stateless
@Path("students")
public class StudentFacadeREST {
    
    @EJB
    private StudentsManagerLocal studentsManager;
    
    public StudentFacadeREST() {
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Student entity) {
        this.studentsManager.createStudent(entity.getFirstName(), entity.getLastName(), entity.getMail(), entity.getPass(), entity.getClasse().getId());
    }
    
    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Student entity) {
        this.studentsManager.updateStudent(entity);
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.studentsManager.deleteStudent(id);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Student find(@PathParam("id") Long id) {
        return this.studentsManager.findStudent(id);
    }
    
    @PUT
    @Path("login")
    @Produces({"application/xml", "application/json"})
    public StudentDTO login(Student entity) {
        System.out.println("YYYYYYYYYYYYYY " + entity.getMail() + " -- " + entity.getPass());
        Student studentFound = this.studentsManager.loginStudent(entity.getMail(), entity.getPass());
        
        StudentDTO sDTO = null;
        if (studentFound != null) {
            sDTO = new StudentDTO();
            sDTO.setId(studentFound.getId());
            sDTO.setFirstName(studentFound.getFirstName());
            sDTO.setLastName(studentFound.getLastName());
            sDTO.setMail(studentFound.getMail());
            //sDTO.setPass(studentFound.getPass());

            ClasseDTO clDTO = new ClasseDTO();
            clDTO.setId(studentFound.getClasse().getId());
            clDTO.setName(studentFound.getClasse().getName());
            List<CoursDTO> listeCoursDTO = new LinkedList<CoursDTO>();
            for (Cours cours : studentFound.getClasse().getListeCours()) {
                CoursDTO coDTO = new CoursDTO();
                coDTO.setId(cours.getId());
                coDTO.setName(cours.getName());
                listeCoursDTO.add(coDTO);
                List<SerieDTO> listeSerieDTO = new LinkedList<SerieDTO>();
                for (Serie serie : cours.getSerie()) {
                    SerieDTO serDTO = new SerieDTO();
                    serDTO.setId(serie.getId());
                    serDTO.setName(serie.getName());
                    listeSerieDTO.add(serDTO);
                }
                coDTO.setSerieDTO(listeSerieDTO);
            }
            clDTO.setListeCours(listeCoursDTO);
            sDTO.setClasse(clDTO);
            
            try {
                ClientConfig cc = new DefaultClientConfig();
                Client client = Client.create(cc);
                WebResource webResource = client.resource("http://localhost:8080/MobWebAppComemStarGame/webresources/players/" + studentFound.getPlayerID());
                ClientResponse response = webResource.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(ClientResponse.class);
                
                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
                
                String output = response.getEntity(String.class);
                System.out.println("PUTPUT "+ output);
                sDTO.setPlayerJSON(output);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        return sDTO;
    }
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<StudentDTO> findAll() {
        List<StudentDTO> liste = new LinkedList<StudentDTO>();
        for (Student student : this.studentsManager.findAll()) {
            StudentDTO sDTO = new StudentDTO();
            sDTO.setId(student.getId());
            sDTO.setFirstName(student.getFirstName());
            sDTO.setLastName(student.getLastName());
            sDTO.setMail(student.getMail());
            //sDTO.setPass(student.getPass());

            ClasseDTO clDTO = new ClasseDTO();
            clDTO.setId(student.getClasse().getId());
            clDTO.setName(student.getClasse().getName());
            List<CoursDTO> listeCoursDTO = new LinkedList<CoursDTO>();
            for (Cours cours : student.getClasse().getListeCours()) {
                
                CoursDTO coDTO = new CoursDTO();
                coDTO.setId(cours.getId());
                coDTO.setName(cours.getName());
                listeCoursDTO.add(coDTO);
            }
            clDTO.setListeCours(listeCoursDTO);
            sDTO.setClasse(clDTO);
            
            liste.add(sDTO);
            
        }
        return liste;
    }
    
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(this.studentsManager.findAll().size());
    }
}
