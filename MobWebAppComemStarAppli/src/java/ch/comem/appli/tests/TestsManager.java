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
        Student chr = this.studentsManager.createStudent("christian", "curchod", "christian.curchod@heig-vd.ch", "1234", 2L);
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
        
        
        Serie serieGenerale     = this.seriesManager.createSerie("Connaissances Générales", coursMWS);
        Serie serieREST         = this.seriesManager.createSerie("REST", coursMWS);
        Serie serieGamification = this.seriesManager.createSerie("Gamification", coursMWS);
        Serie serieLayer        = this.seriesManager.createSerie("Layers", coursIHM);
        Serie serieActions      = this.seriesManager.createSerie("Actions", coursIHM);
        Serie serieRecursivite  = this.seriesManager.createSerie("Recursivité", coursIHM);
        Serie serieIntroPOO     = this.seriesManager.createSerie("Introduction aux classes", coursPOO);
        Serie serieHeritage     = this.seriesManager.createSerie("Heritage", coursPOO);
        Serie serieImplement    = this.seriesManager.createSerie("Extends ou Implements", coursPOO);
        Serie serieNewsProcess  = this.seriesManager.createSerie("Prcessus de créations d'un journal", coursNEWS);
        Serie serieNewsPerti    = this.seriesManager.createSerie("Pertinance des articles", coursNEWS);
        
        
//        Question q1 = this.questionsManager.createQuestion("JEE: Combien font 2x2 ?", serieIntroJEE);
//        this.answersManager.createAnswer("42", Boolean.FALSE, q1);
//        this.answersManager.createAnswer("4", Boolean.TRUE, q1);
//        this.answersManager.createAnswer("Demande à Chuck Norris", Boolean.FALSE, q1);
//        
//        Question q2 = this.questionsManager.createQuestion("JEE: Combien font 3+10 ?", serieIntroJEE);
//        this.answersManager.createAnswer("13", Boolean.TRUE, q2);
//        this.answersManager.createAnswer("4", Boolean.FALSE, q2);
//        this.answersManager.createAnswer("Demande à Chuck Norris", Boolean.FALSE, q2);
//        
//        Question q3 = this.questionsManager.createQuestion("REST: Combien font 6x7 ?", serieREST);
//        this.answersManager.createAnswer("42", Boolean.TRUE, q3);
//        this.answersManager.createAnswer("4", Boolean.FALSE, q3);
//        this.answersManager.createAnswer("Demande à Chuck Norris", Boolean.FALSE, q3);
        
        Question q11 = this.questionsManager.createQuestion("Qu'est ce que la 'Gamification'?", serieGenerale);
        this.answersManager.createAnswer("Une religion orientée sur les jeux vidéo", Boolean.FALSE, q11);
        this.answersManager.createAnswer("L'utilisation de mécanisme de jeu dans des applications diverses afin d'augmenter l'intéret, et l'engagement de l'utilisateur", Boolean.TRUE, q11);
        this.answersManager.createAnswer("Une technique de programmation utilisée pour coder des systèmes holographiques", Boolean.FALSE, q11);
        
        Question q21 = this.questionsManager.createQuestion("Lequel de ces éléments n'est pas un composant d'une plateforme de 'Gamification'?", serieGenerale);
        this.answersManager.createAnswer("REST API", Boolean.FALSE, q21);
        this.answersManager.createAnswer("Game engine", Boolean.FALSE, q21);
        this.answersManager.createAnswer("Havok engine", Boolean.TRUE, q21);
        
        Question q31 = this.questionsManager.createQuestion("Quelle entitée ne fait pas parti d'un Game engine?", serieGenerale);
        this.answersManager.createAnswer("Event", Boolean.FALSE, q31);
        this.answersManager.createAnswer("Badge", Boolean.FALSE, q31);
        this.answersManager.createAnswer("Comment", Boolean.TRUE, q31);
        
        Question q41 = this.questionsManager.createQuestion("Qu'est ce que Glassfish ?", serieGenerale);
        this.answersManager.createAnswer("Un poisson qui vit uniquement dans les vers de 3dl", Boolean.FALSE, q41);
        this.answersManager.createAnswer("Un serveur d'applications Open-source", Boolean.TRUE, q41);
        this.answersManager.createAnswer("Un Web Service pour les bases de données NOSQL", Boolean.FALSE, q41);
        
        Question q51 = this.questionsManager.createQuestion("Pour ajouter un nouvel enregistrement à l'aide d'un service REST, il faut utiliser l'annotation:", serieGenerale);
        this.answersManager.createAnswer("POST", Boolean.TRUE, q51);
        this.answersManager.createAnswer("GET", Boolean.FALSE, q51);
        this.answersManager.createAnswer("PUT", Boolean.FALSE, q51);
        
        Question q61 = this.questionsManager.createQuestion("ORM signifie:", serieGenerale);
        this.answersManager.createAnswer("Object Relational Mapping", Boolean.TRUE, q61);
        this.answersManager.createAnswer("Open Rules Model", Boolean.FALSE, q61);
        this.answersManager.createAnswer("Object Rules Mapping", Boolean.FALSE, q61);
        
        Question q71 = this.questionsManager.createQuestion("Que sont les 'servelet'?", serieGenerale);
        this.answersManager.createAnswer("Une technologie JAVA utilisée pour effectuer des traitements coté serveur en réponse aux requêtes provenant en général de postes clients distants", Boolean.TRUE, q71);
        this.answersManager.createAnswer("Des services Web permettant de simuler une intelligence artificielle", Boolean.FALSE, q71);
        this.answersManager.createAnswer("Une Technologie IBM conçue pour remplacer le SQL en utilisant la RAM de l'ordinateur", Boolean.FALSE, q71);
        
        Question q81 = this.questionsManager.createQuestion("Laquelle de ces propositions n'est pas une qualité systemique?", serieGenerale);
        this.answersManager.createAnswer("le temps de réponse", Boolean.FALSE, q81);
        this.answersManager.createAnswer("la disponibilité", Boolean.FALSE, q81);
        this.answersManager.createAnswer("la créativité", Boolean.TRUE, q81);
        
        Question q91 = this.questionsManager.createQuestion("Le logiciel qui implémente la spécification Java EE est appelé:", serieGenerale);
        this.answersManager.createAnswer("application serveur", Boolean.TRUE, q91);
        this.answersManager.createAnswer("application multiplateforme", Boolean.FALSE, q91);
        this.answersManager.createAnswer("service entreprise", Boolean.FALSE, q91);
        
        Question q101 = this.questionsManager.createQuestion("JEE propose une approche:", serieGenerale);
        this.answersManager.createAnswer("multi threading", Boolean.FALSE, q101);
        this.answersManager.createAnswer("multi niveaux", Boolean.TRUE, q101);
        this.answersManager.createAnswer("linéaire", Boolean.FALSE, q101);
        
        Question q12 = this.questionsManager.createQuestion("Qu'est ce que la 'Gamification'?", serieREST);
        this.answersManager.createAnswer("Une religion orientée sur les jeux vidéo", Boolean.FALSE, q12);
        this.answersManager.createAnswer("L'utilisation de mécanisme de jeu dans des applications diverses afin d'augmenter l'intéret, et l'engagement de l'utilisateur", Boolean.TRUE, q12);
        this.answersManager.createAnswer("Une technique de programmation utilisée pour coder des systèmes holographiques", Boolean.FALSE, q12);
        
        Question q22 = this.questionsManager.createQuestion("Lequel de ces éléments n'est pas un composant d'une plateforme de 'Gamification'?", serieREST);
        this.answersManager.createAnswer("REST API", Boolean.FALSE, q22);
        this.answersManager.createAnswer("Game engine", Boolean.FALSE, q22);
        this.answersManager.createAnswer("Havok engine", Boolean.TRUE, q22);
        
        Question q32 = this.questionsManager.createQuestion("Quelle entitée ne fait pas parti d'un Game engine?", serieREST);
        this.answersManager.createAnswer("Event", Boolean.FALSE, q32);
        this.answersManager.createAnswer("Badge", Boolean.FALSE, q32);
        this.answersManager.createAnswer("Comment", Boolean.TRUE, q32);
        
        Question q42 = this.questionsManager.createQuestion("Qu'est ce que Glassfish ?", serieREST);
        this.answersManager.createAnswer("Un poisson qui vit uniquement dans les vers de 3dl", Boolean.FALSE, q42);
        this.answersManager.createAnswer("Un serveur d'applications Open-source", Boolean.TRUE, q42);
        this.answersManager.createAnswer("Un Web Service pour les bases de données NOSQL", Boolean.FALSE, q42);
        
        /*Question q52 = this.questionsManager.createQuestion("Pour ajouter un nouvel enregistrement à l'aide d'un service REST, il faut utiliser l'annotation:", serieREST);
        this.answersManager.createAnswer("POST", Boolean.TRUE, q52);
        this.answersManager.createAnswer("GET", Boolean.FALSE, q52);
        this.answersManager.createAnswer("PUT", Boolean.FALSE, q52);
        
        Question q62 = this.questionsManager.createQuestion("ORM signifie:", serieREST);
        this.answersManager.createAnswer("Object Relational Mapping", Boolean.TRUE, q62);
        this.answersManager.createAnswer("Open Rules Model", Boolean.FALSE, q62);
        this.answersManager.createAnswer("Object Rules Mapping", Boolean.FALSE, q62);
        
        Question q72 = this.questionsManager.createQuestion("Que sont les 'servelet'?", serieREST);
        this.answersManager.createAnswer("Une technologie JAVA utilisée pour effectuer des traitements coté serveur en réponse aux requêtes provenant en général de postes clients distants", Boolean.TRUE, q72);
        this.answersManager.createAnswer("Des services Web permettant de simuler une intelligence artificielle", Boolean.FALSE, q72);
        this.answersManager.createAnswer("Une Technologie IBM conçue pour remplacer le SQL en utilisant la RAM de l'ordinateur", Boolean.FALSE, q72);
        
        Question q82 = this.questionsManager.createQuestion("Laquelle de ces propositions n'est pas une qualité systemique?", serieREST);
        this.answersManager.createAnswer("le temps de réponse", Boolean.FALSE, q82);
        this.answersManager.createAnswer("la disponibilité", Boolean.FALSE, q82);
        this.answersManager.createAnswer("la créativité", Boolean.TRUE, q82);
        
        Question q92 = this.questionsManager.createQuestion("Le logiciel qui implémente la spécification Java EE est appelé:", serieREST);
        this.answersManager.createAnswer("application serveur", Boolean.TRUE, q92);
        this.answersManager.createAnswer("application multiplateforme", Boolean.FALSE, q92);
        this.answersManager.createAnswer("service entreprise", Boolean.FALSE, q92);
        
        Question q102 = this.questionsManager.createQuestion("JEE propose une approche:", serieREST);
        this.answersManager.createAnswer("multi threading", Boolean.FALSE, q102);
        this.answersManager.createAnswer("multi niveaux", Boolean.TRUE, q102);
        this.answersManager.createAnswer("linéaire", Boolean.FALSE, q102);*/
        
        Question q13 = this.questionsManager.createQuestion("Qu'est ce que la 'Gamification'?", serieGamification);
        this.answersManager.createAnswer("Une religion orientée sur les jeux vidéo", Boolean.FALSE, q13);
        this.answersManager.createAnswer("L'utilisation de mécanisme de jeu dans des applications diverses afin d'augmenter l'intéret, et l'engagement de l'utilisateur", Boolean.TRUE, q13);
        this.answersManager.createAnswer("Une technique de programmation utilisée pour coder des systèmes holographiques", Boolean.FALSE, q13);
        
        Question q23 = this.questionsManager.createQuestion("Lequel de ces éléments n'est pas un composant d'une plateforme de 'Gamification'?", serieGamification);
        this.answersManager.createAnswer("REST API", Boolean.FALSE, q23);
        this.answersManager.createAnswer("Game engine", Boolean.FALSE, q23);
        this.answersManager.createAnswer("Havok engine", Boolean.TRUE, q23);
        
        Question q33 = this.questionsManager.createQuestion("Quelle entitée ne fait pas parti d'un Game engine?", serieGamification);
        this.answersManager.createAnswer("Event", Boolean.FALSE, q33);
        this.answersManager.createAnswer("Badge", Boolean.FALSE, q33);
        this.answersManager.createAnswer("Comment", Boolean.TRUE, q33);
        
        /*Question q43 = this.questionsManager.createQuestion("Qu'est ce que Glassfish ?", serieGamification);
        this.answersManager.createAnswer("Un poisson qui vit uniquement dans les vers de 3dl", Boolean.FALSE, q43);
        this.answersManager.createAnswer("Un serveur d'applications Open-source", Boolean.TRUE, q43);
        this.answersManager.createAnswer("Un Web Service pour les bases de données NOSQL", Boolean.FALSE, q43);
        
        Question q53 = this.questionsManager.createQuestion("Pour ajouter un nouvel enregistrement à l'aide d'un service REST, il faut utiliser l'annotation:", serieGamification);
        this.answersManager.createAnswer("POST", Boolean.TRUE, q53);
        this.answersManager.createAnswer("GET", Boolean.FALSE, q53);
        this.answersManager.createAnswer("PUT", Boolean.FALSE, q53);
        
        Question q63 = this.questionsManager.createQuestion("ORM signifie:", serieGamification);
        this.answersManager.createAnswer("Object Relational Mapping", Boolean.TRUE, q63);
        this.answersManager.createAnswer("Open Rules Model", Boolean.FALSE, q63);
        this.answersManager.createAnswer("Object Rules Mapping", Boolean.FALSE, q63);
        
        Question q73 = this.questionsManager.createQuestion("Que sont les 'servelet'?", serieGamification);
        this.answersManager.createAnswer("Une technologie JAVA utilisée pour effectuer des traitements coté serveur en réponse aux requêtes provenant en général de postes clients distants", Boolean.TRUE, q73);
        this.answersManager.createAnswer("Des services Web permettant de simuler une intelligence artificielle", Boolean.FALSE, q73);
        this.answersManager.createAnswer("Une Technologie IBM conçue pour remplacer le SQL en utilisant la RAM de l'ordinateur", Boolean.FALSE, q73);
        
        Question q83 = this.questionsManager.createQuestion("Laquelle de ces propositions n'est pas une qualité systemique?", serieGamification);
        this.answersManager.createAnswer("le temps de réponse", Boolean.FALSE, q83);
        this.answersManager.createAnswer("la disponibilité", Boolean.FALSE, q83);
        this.answersManager.createAnswer("la créativité", Boolean.TRUE, q83);
        
        Question q93 = this.questionsManager.createQuestion("Le logiciel qui implémente la spécification Java EE est appelé:", serieGamification);
        this.answersManager.createAnswer("application serveur", Boolean.TRUE, q93);
        this.answersManager.createAnswer("application multiplateforme", Boolean.FALSE, q93);
        this.answersManager.createAnswer("service entreprise", Boolean.FALSE, q93);
        
        Question q103 = this.questionsManager.createQuestion("JEE propose une approche:", serieGamification);
        this.answersManager.createAnswer("multi threading", Boolean.FALSE, q103);
        this.answersManager.createAnswer("multi niveaux", Boolean.TRUE, q103);
        this.answersManager.createAnswer("linéaire", Boolean.FALSE, q103);*/
        
        Question q14 = this.questionsManager.createQuestion("Que signifie 'Layers'?", serieLayer);
        this.answersManager.createAnswer("Couches", Boolean.TRUE, q14);
        this.answersManager.createAnswer("Souches", Boolean.FALSE, q14);
        this.answersManager.createAnswer("Vues", Boolean.FALSE, q14);
        
        Question q24 = this.questionsManager.createQuestion("Que signifie IHM?", serieLayer);
        this.answersManager.createAnswer("Internet Haute Modularité", Boolean.FALSE, q24);
        this.answersManager.createAnswer("Interface Homme Machine", Boolean.TRUE, q24);
        this.answersManager.createAnswer("Individu Hyper Méchant", Boolean.FALSE, q24);
        
        Question q34 = this.questionsManager.createQuestion("Qu'est ce que JAVA?", serieLayer);
        this.answersManager.createAnswer("Un type de dance", Boolean.FALSE, q34);
        this.answersManager.createAnswer("Un language de programmation", Boolean.TRUE, q34);
        this.answersManager.createAnswer("Une ile", Boolean.FALSE, q34);
        
        Question q15 = this.questionsManager.createQuestion("Parmis c'est propositions, laquelle ne fait pas parti de la liste des intefaces évenements de JDK 1.4.2?", serieActions);
        this.answersManager.createAnswer("KeyListener", Boolean.FALSE, q14);
        this.answersManager.createAnswer("CatListener", Boolean.TRUE, q14);
        this.answersManager.createAnswer("WindowListener", Boolean.FALSE, q14);
        
        Question q25 = this.questionsManager.createQuestion("Quel package est inclu par défaut dans toutes les versions JAVA?", serieActions);
        this.answersManager.createAnswer("R2D2", Boolean.FALSE, q24);
        this.answersManager.createAnswer("AWT ", Boolean.TRUE, q24);
        this.answersManager.createAnswer("K2000", Boolean.FALSE, q24);
        
        Question q35 = this.questionsManager.createQuestion("Quel film d'action à pour héro John McClane?", serieActions);
        this.answersManager.createAnswer("Die Hard", Boolean.TRUE, q34);
        this.answersManager.createAnswer("Mission Impossible", Boolean.FALSE, q34);
        this.answersManager.createAnswer("Jurassic Park", Boolean.FALSE, q34);
        
        
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
