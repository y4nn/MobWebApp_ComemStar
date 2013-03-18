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
//@WebService
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

    
    

}
