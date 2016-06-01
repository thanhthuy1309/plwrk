package controllers;

import java.util.List;

import static play.libs.Json.toJson;

import javax.inject.Inject;

import models.Students;
import play.data.FormFactory;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import dto.StudentsDto;

public class Application extends Controller {
	
	
    @Inject
    FormFactory formFactory;
    
    @Transactional(readOnly = true)
    public Result index() {
    	List<Students> students = (List<Students>) JPA.em().createQuery("select p from Students p").getResultList();
        return ok(index.render(students));
    }

    @Transactional
    public Result addStudents() {
        StudentsDto studentsDto = formFactory.form(StudentsDto.class).bindFromRequest().get();
        Students p = new Students();
        p.setName(studentsDto.getName());
        JPA.em().persist(p);
        return redirect(routes.Application.index());
    }

    @Transactional(readOnly = true)
    public Result getStudents() {
        List<Students> students = (List<Students>) JPA.em().createQuery("select p from Students p").getResultList();
        return ok(toJson(students));
    }
}
