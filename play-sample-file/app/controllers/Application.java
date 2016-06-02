package controllers;

import static play.libs.Json.toJson;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import models.Students;

import org.apache.commons.io.FileUtils;

import play.Play;
import play.data.FormFactory;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import views.html.index;
import dto.StudentsDto;

public class Application extends Controller {
	
	
    @Inject
    FormFactory formFactory;
    
    @Transactional(readOnly = true)
    public Result index() {
    	List<Students> students = (List<Students>) JPA.em().createNamedQuery("AccountAuthorityService.findAccountAuthorities").getResultList();
        return ok(index.render(students));
    }

    @Transactional
    public Result addStudents() {
    	MultipartFormData<File> body = request().body().asMultipartFormData();
    	// lay gia tri cua field name = image
        MultipartFormData.FilePart<File> picture = body.getFile("image");
        if (picture != null) {
            File file = picture.getFile();
            // su dung lay duong dan hien tai cua project 
			String path = Play.application().path().getPath();
			// file image duoc upload vao thu muc public/upload/
            File destination = new File(path + "/public/upload/", picture.getFilename());
            try {
				FileUtils.moveFile(file, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        StudentsDto studentsDto = formFactory.form(StudentsDto.class).bindFromRequest().get();
	        Students p = new Students();
	        p.setName(studentsDto.getName());
	        p.setImage("upload/"+picture.getFilename());
	        JPA.em().persist(p);
	        return redirect(routes.Application.index());
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    @Transactional(readOnly = true)
    public Result getStudents() {
        List<Students> students = (List<Students>) JPA.em().createQuery("select p from Students p").getResultList();
        return ok(toJson(students));
    }
}
