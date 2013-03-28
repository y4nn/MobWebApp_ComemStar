/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.appli.tests;

import ch.comem.appli.model.Classe;
import ch.comem.appli.model.Cours;
import ch.comem.appli.model.Question;
import ch.comem.appli.model.Serie;
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
    public void populateDB() {
        Classe mit39 = this.classesManager.createClasse("MIT39");
        Classe mm39 = this.classesManager.createClasse("MM39");

        Student zik = this.studentsManager.createStudent("zikrija", "saric", "zikrija.saric@heig-vd.ch", "pass", 1L);
        Student yann = this.studentsManager.createStudent("yann", "maillard", "yann.maillard@heig-vd.ch", "pass", 1L);
        Student fab = this.studentsManager.createStudent("fabien", "cornaz", "fabien.cornaz@heig-vd.ch", "pass", 1L);
        Student chr = this.studentsManager.createStudent("christophe", "chuchaud", "christophe.curchaud@heig-vd.ch", "pass", 2L);
        Student san = this.studentsManager.createStudent("sandra", "gindroz", "sandra.gindroz@heig-vd.ch", "pass", 2L);
        
        
//        this.classesManager.addStudent(mit39, zik);
//        this.classesManager.addStudent(mit39, yann);
//        this.classesManager.addStudent(mit39, fab);
//        this.classesManager.addStudent(mm39, chr);
//        this.classesManager.addStudent(mm39, san);
        
        
                
        

        
        
        //this.coursesManager.createCours("MobileWebService", null, listeClasses);
        //Cours coursMWS = this.coursesManager.createCours("Mobile Web Service");
        //this.coursesManager.addClasse(coursMWS, mm39);
        //this.coursesManager.addClasse(coursMWS, mit39);
        Classe ig38 = this.classesManager.createClasse("IG38");
        
        Cours coursMWS = this.coursesManager.createCours("Mobile WEB Service");
        Cours coursIHM = this.coursesManager.createCours("IHM");
        Cours coursPOO = this.coursesManager.createCours("POO");
        Cours coursNEWS = this.coursesManager.createCours("Atelier NEWS Room");
        Cours coursFI = this.coursesManager.createCours("Finances et Investissements");
        
        this.classesManager.addCours(mm39, coursPOO);
        this.classesManager.addCours(mm39, coursMWS);
        this.classesManager.addCours(mm39, coursNEWS);
        this.coursesManager.addClasse(coursIHM, mit39);
        this.coursesManager.addClasse(coursMWS, mit39);
        this.coursesManager.addClasse(coursPOO, mit39);
        this.coursesManager.addClasse(coursFI, ig38);
        
        
        Serie serieIntroJEE     = this.seriesManager.createSerie("Intro JEE", coursMWS);
        Serie serieREST         = this.seriesManager.createSerie("REST", coursMWS);
        Serie serieLayer        = this.seriesManager.createSerie("Layers", coursIHM);
        Serie serieActions      = this.seriesManager.createSerie("Actions", coursIHM);
        Serie serieRecursivite  = this.seriesManager.createSerie("Recursivité", coursIHM);
        Serie serieIntroPOO     = this.seriesManager.createSerie("Introduction aux classes", coursPOO);
        Serie serieHeritage     = this.seriesManager.createSerie("Heritage", coursPOO);
        Serie serieImplement    = this.seriesManager.createSerie("Extends ou Implements", coursPOO);
        Serie serieNewsProcess  = this.seriesManager.createSerie("Prcessus de créations d'un journal", coursNEWS);
        Serie serieNewsPerti    = this.seriesManager.createSerie("Pertinance des articles", coursNEWS);
        
        
        Question q1 = this.questionsManager.createQuestion("Combien font 2x2 ?", serieIntroJEE);
        this.answersManager.createAnswer("42", Boolean.FALSE, q1);
        this.answersManager.createAnswer("4", Boolean.TRUE, q1);
        this.answersManager.createAnswer("21", Boolean.FALSE, q1);
        
        Question q2 = this.questionsManager.createQuestion("Combien font 3+10 ?", serieIntroJEE);
        this.answersManager.createAnswer("13", Boolean.TRUE, q2);
        this.answersManager.createAnswer("4", Boolean.FALSE, q2);
        this.answersManager.createAnswer("17", Boolean.FALSE, q2);
        
        Question q3 = this.questionsManager.createQuestion("Combien font 3x3 ?", serieIntroJEE);
        this.answersManager.createAnswer("27", Boolean.FALSE, q3);
        this.answersManager.createAnswer("9", Boolean.TRUE, q3);
        this.answersManager.createAnswer("6", Boolean.FALSE, q3);
        
        Question q4 = this.questionsManager.createQuestion("Combien font 3x0 ?", serieIntroJEE);
        this.answersManager.createAnswer("0", Boolean.TRUE, q4);
        this.answersManager.createAnswer("4", Boolean.FALSE, q4);
        this.answersManager.createAnswer("3", Boolean.FALSE, q4);
        
        Question q5 = this.questionsManager.createQuestion("Combien font 8/2 ?", serieIntroJEE);
        this.answersManager.createAnswer("6", Boolean.FALSE, q5);
        this.answersManager.createAnswer("2", Boolean.FALSE, q5);
        this.answersManager.createAnswer("4", Boolean.TRUE, q5);
        
        /*Question q6 = this.questionsManager.createQuestion("Combien font 13*2 ?", serieIntroJEE);
        this.answersManager.createAnswer("13", Boolean.FALSE, q6);
        this.answersManager.createAnswer("26", Boolean.TRUE, q6);
        this.answersManager.createAnswer("27", Boolean.FALSE, q6);
        
        Question q7 = this.questionsManager.createQuestion("Combien font 8x9 ?", serieIntroJEE);
        this.answersManager.createAnswer("98", Boolean.FALSE, q7);
        this.answersManager.createAnswer("17", Boolean.FALSE, q7);
        this.answersManager.createAnswer("81", Boolean.TRUE, q7);
        
        Question q8 = this.questionsManager.createQuestion("Combien font 8x8 ?", serieIntroJEE);
        this.answersManager.createAnswer("64", Boolean.TRUE, q8);
        this.answersManager.createAnswer("16", Boolean.FALSE, q8);
        this.answersManager.createAnswer("88", Boolean.FALSE, q8);
        
        Question q9 = this.questionsManager.createQuestion("Combien font 9+1 ?", serieIntroJEE);
        this.answersManager.createAnswer("10", Boolean.TRUE, q9);
        this.answersManager.createAnswer("4", Boolean.FALSE, q9);
        this.answersManager.createAnswer("11", Boolean.FALSE, q9);
        
        Question q10 = this.questionsManager.createQuestion("Combien font 7-4 ?", serieIntroJEE);
        this.answersManager.createAnswer("10", Boolean.FALSE, q10);
        this.answersManager.createAnswer("3", Boolean.TRUE, q10);
        this.answersManager.createAnswer("14", Boolean.FALSE, q10);*/
        
        Question q0 = this.questionsManager.createQuestion("REST: Combien font 6x7 ?", serieREST);
        this.answersManager.createAnswer("42", Boolean.TRUE, q0);
        this.answersManager.createAnswer("4", Boolean.FALSE, q0);
        this.answersManager.createAnswer("Demande à Chuck Norris", Boolean.FALSE, q0);
        
        
    }

    @Override
    public void createClassesCours() {
        Classe mit39 = this.classesManager.createClasse("MIT39");
        Classe mm39 = this.classesManager.createClasse("MM39");
        Classe ig38 = this.classesManager.createClasse("IG38");
        
        Cours coursMWS = this.coursesManager.createCours("Mobile WEB Service");
        Cours coursIHM = this.coursesManager.createCours("IHM");
        Cours coursPOO = this.coursesManager.createCours("POO");
        Cours coursNEWS = this.coursesManager.createCours("Atelier NEWS Room");
        Cours coursFI = this.coursesManager.createCours("Finances et Investissements");
        
        this.classesManager.addCours(mm39, coursPOO);
        this.classesManager.addCours(mm39, coursMWS);
        this.classesManager.addCours(mm39, coursNEWS);
        this.coursesManager.addClasse(coursIHM, mit39);
        this.coursesManager.addClasse(coursMWS, mit39);
        this.coursesManager.addClasse(coursPOO, mit39);
        this.coursesManager.addClasse(coursFI, ig38);
    }

    @Override
    public void deleteCours(Long idCours) {
        this.coursesManager.deleteCours(idCours);
    }

    
    

}
