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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Ziki
 */
@Stateless
@Path("series")
public class SerieFacadeREST {

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

    @POST
    @Path("end")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public void EndOfSerie(JSONObject jso) {
        try {
            String eventType = "";
            Long serieId = jso.getLong("serieId");
            int score = jso.getInt("score");
            int applicationId = jso.getInt("applicationId");
            int playerId = jso.getInt("playerId");

            if (score < 50) {
                eventType = "Serie " + serieId + " terminee avec un score inférieur à 50%";
            }
            if ((score >= 50) && (score < 70)) {
                eventType = "Serie " + serieId + " terminee avec un score compris entre 50% et 70%";
            }
            if ((score >= 70) && (score < 90)) {
                eventType = "Serie " + serieId + " terminee avec un score compris entre 70% et 90%";
            }
            if ((score >= 90) && (score <= 99)) {
                eventType = "Serie " + serieId + " terminee avec un score compris entre 90% et 99%";
            }
            if ((score == 100)) {
                eventType = "Serie " + serieId + " terminee avec un score parfait";
            }
            ClientConfig cc = new DefaultClientConfig();
            Client client = Client.create(cc);

            WebResource webResource = client.resource("http://localhost:8080/MobWebAppComemStarGame/webresources/events");
            String jsonObject = "{"
                    + "\"type\": \"" + eventType + "\","
                    + "\"application\": {"
                    + "\"id\": \"" + applicationId + "\","
                    + "\"name\": \"comemstar\","
                    + "\"description\": \"application qui vous rend plus intelligent\""
                    + "},"
                    + "\"player\": {"
                    + "\"id\": \"" + playerId + "\""
                    + "}"
                    + "}";

            ClientResponse response = webResource.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonObject);

            if (response.getStatus() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }


            WebResource webResource2 = client.resource("http://localhost:8080/MobWebAppComemStarGame/webresources/players/" + playerId);
            ClientResponse response2 = webResource2.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(ClientResponse.class);

            if (response2.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response2.getStatus());
            }

            JSONObject jsoPlayer = null;
            jsoPlayer = response2.getEntity(JSONObject.class);

            Serie serie = this.seriesManager.findSerie(serieId);
            Long coursId = serie.getCours().getId();

            List<Serie> listeSeriesRecherchee = new LinkedList<Serie>();
            listeSeriesRecherchee = this.seriesManager.findByCours(coursId);

            JSONArray listeBadges = jsoPlayer.getJSONArray("listeBadges");
            System.out.println(listeBadges);
            int nombreBadgesSerie = 0;
            for (int i = 0; i < listeSeriesRecherchee.size(); i++) {
                for (int j = 0; j < listeBadges.length(); j++) {

                    if (listeSeriesRecherchee.get(j).getName().equals(listeBadges.getJSONObject(i).getString("name")) && listeBadges.getJSONObject(i).getString("icone").equals("diamond")) {
                        nombreBadgesSerie++;
                        System.out.println(nombreBadgesSerie);
                    }
                }
            }
            System.out.println(nombreBadgesSerie + "=" + listeBadges.length());
            if (nombreBadgesSerie == listeBadges.length()) {
                System.out.println("RENTRE DANS LE IF POUR CREER L'EVENT");
                WebResource webResource3 = client.resource("http://localhost:8080/MobWebAppComemStarGame/webresources/events");

                String eventType3 = "a obtenu tous les badges en diamand du cours " + coursId;
                String jsonObject3 = "{"
                        + "\"type\": \"" + eventType3 + "\","
                        + "\"application\": {"
                        + "\"id\": \"" + applicationId + "\","
                        + "\"name\": \"comemstar\","
                        + "\"description\": \"application qui vous rend plus intelligent\""
                        + "},"
                        + "\"player\": {"
                        + "\"id\": \"" + playerId + "\""
                        + "}"
                        + "}";

                ClientResponse response3 = webResource3.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonObject3);

                if (response3.getStatus() != 204) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response3.getStatus());
                }
            }


        } catch (JSONException ex) {
            Logger.getLogger(SerieFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
