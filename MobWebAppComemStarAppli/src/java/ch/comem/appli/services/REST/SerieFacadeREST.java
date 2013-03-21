/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.services.REST;

import ch.comem.appli.dto.AnswerDTO;
import ch.comem.appli.dto.QuestionDTO;
import ch.comem.appli.dto.SerieDTO;
import ch.comem.appli.model.Answer;
import ch.comem.appli.model.Question;
import ch.comem.appli.model.Serie;
import ch.comem.appli.services.SeriesManagerLocal;
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
@Path("series")
public class SerieFacadeREST  {
    @EJB
    private SeriesManagerLocal seriesManager;
    
    public SerieFacadeREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Serie entity) {
        this.seriesManager.createSerie(entity.getName(), entity.getCours());
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Serie entity) {
        this.seriesManager.updateSerie(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.seriesManager.deleteSerie(id);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<QuestionDTO> find(@PathParam("id") Long id) {
        List<QuestionDTO> liste = new LinkedList<QuestionDTO>();
        for (Question question : this.seriesManager.findSerie(id).getQuestion()) {
            QuestionDTO qdto = new QuestionDTO();
            qdto.setId(question.getId());
            qdto.setQuestion(question.getQuestion());
            List<AnswerDTO> listAnswers = new LinkedList<AnswerDTO>();
            for (Answer answer : question.getReponse()) {
                AnswerDTO adto = new AnswerDTO();
                adto.setAnswer(answer.getAnswer());
                adto.setId(answer.getId());
                adto.setIsValid(answer.getIsValid());
                listAnswers.add(adto);
            }
            qdto.setReponse(listAnswers);
            liste.add(qdto);
        }
        return liste;
    }
    
    @GET
    @Path("cours/{id}")
    @Produces({"application/xml", "application/json"})
    public List<SerieDTO> findByCours(@PathParam("id") Long id) {
        List<SerieDTO> liste = new LinkedList<SerieDTO>();
        for (Serie serie : this.seriesManager.findByCours(id)) {
            SerieDTO sDTO = new SerieDTO();
            sDTO.setId(serie.getId());
            sDTO.setName(serie.getName());
            liste.add(sDTO);
        }
        return liste;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<SerieDTO> findAll() {
        List<SerieDTO> liste = new LinkedList<SerieDTO>();
        for (Serie serie : this.seriesManager.findAll()) {
            SerieDTO sDTO = new SerieDTO();
            sDTO.setId(serie.getId());
            sDTO.setName(serie.getName());
            liste.add(sDTO);
        }
        
        return liste;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(this.seriesManager.findAll().size());
    }
}
