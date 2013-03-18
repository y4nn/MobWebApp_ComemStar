/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.tests;

import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Student;
import ch.comem.appli.services.AnswersManagerLocal;
import ch.comem.appli.services.ClassesManagerLocal;
import ch.comem.appli.services.CoursesManagerLocal;
import ch.comem.appli.services.QuestionsManagerLocal;
import ch.comem.appli.services.SeriesManagerLocal;
import ch.comem.appli.services.StudentsManagerLocal;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Ziki
 */
@Stateless
@WebService
public class TestsManager implements TestsManagerLocal {
    
    @EJB
    private SeriesManagerLocal seriesManager;
    @EJB
    private QuestionsManagerLocal questionsManager;
    @EJB
    private CoursesManagerLocal coursesManager;
    @EJB
    private ClassesManagerLocal classesManager;
    @EJB
    private AnswersManagerLocal answersManager;
    @EJB
    private StudentsManagerLocal studentsManager;
    
    @Override
    public void generateStudents() {
        for (int i = 0; i < 100; i++) {
            this.studentsManager.createStudent("firstName #"+i, "lastName #"+i, i+"@"+i+".ch", "passss"+i, this.classesManager.createClasse("mit39", null, null));
        }
    }
    
    @Override
    public void generateClasses() {
        //for (int i = 0; i < 100; i++) {
            int i=0;
            List<Student> listeEtudiant = new LinkedList<Student>();
            listeEtudiant.add(this.studentsManager.createStudent("ziki "+i, "ziki "+i, "ziki "+i, "ziki "+i, null));
            listeEtudiant.add(this.studentsManager.createStudent("miki "+i, "miki "+i, "miki "+i, "miki "+i, null));
            List<Cours> listeCours = new LinkedList<Cours>();
            listeCours.add(this.coursesManager.createCours("IHM"));
            listeCours.add(this.coursesManager.createCours("Mobile Web Service"));
            listeCours.add(this.coursesManager.createCours("ITIL"));
            
            
            this.classesManager.createClasse("mit39", listeEtudiant, listeCours);
        //}
    }
    
    @Override
    public void generateCourses() {
        for (int i = 0; i < 100; i++) {
            this.coursesManager.createCours("cours #"+i);
        }
    }
    
    @Override
    public void generateSeries() {
        for (int i = 0; i < 100; i++) {
            this.seriesManager.createSerie("serie #"+i);
        }
    }
    
    @Override
    public void generateQuestions() {
        for (int i = 0; i < 100; i++) {
            this.questionsManager.createQuestion("question #"+i);
        }
    }
    
    @Override
    public void generateAnswers() {
        for (int i = 0; i < 100; i++) {
            this.answersManager.createAnswer("answer #"+i, true);
        }
    }

}
