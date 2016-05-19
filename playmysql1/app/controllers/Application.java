package controllers;

import static play.libs.Json.toJson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import models.Person;
import models.Student;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.i18n.Lang;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import dto.PersonDto;

public class Application extends Controller {

    @Inject
    FormFactory formFactory;
    
    static List<String> departments;
    static List<String> sex;
    static {
    	departments = new ArrayList<String>();
    	departments.add("Information Technology");
    	departments.add("Computer Engineering");
    	departments.add("Electronix & TeleCommunications");
    	departments.add("Production Engineering");
    	departments.add("Instrumentation Engineering");
    	departments.add("Mechanical Engineering");
    	departments.add("Chemical Engineering");
    	departments.add("Textile Engineering");
    	
    	sex = new ArrayList<String>();
    	sex.add("Male");
    	sex.add("Female");

    }
    @Transactional(readOnly = true)
    public Result index() {
    	List<Person> persons = (List<Person>) JPA.em().createQuery("select p from Person p").getResultList();
        return ok(index.render(persons));
    }

    @Transactional
    public Result addPerson() {
        PersonDto personDto = formFactory.form(PersonDto.class).bindFromRequest().get();
        Person p = new Person();
        p.setName(personDto.getName());
        JPA.em().persist(p);
        
        return redirect(routes.Application.index());
    }

    @Transactional(readOnly = true)
    public Result getPersons() {
        List<Person> persons = (List<Person>) JPA.em().createQuery("select p from Person p").getResultList();
        return ok(toJson(persons));
    }

    @Transactional(readOnly = true)
    public Result listStudent() {
    	List<Student> students = (List<Student>) JPA.em().createQuery("select s from Student s").getResultList();
        return ok(views.html.listStudent.render(students));
    }
    
    @Transactional(readOnly = true)
    public Result students() {
    	List<Student> students = (List<Student>) JPA.em().createQuery("select s from Student s").getResultList();
        return ok(toJson(students));
    }

    public Result addStudentGet() {
    	Form<Student> studentForm = Form.form(Student.class);
    	return ok(views.html.addStudent.render(studentForm, departments, sex));
    }

    /**
	 * This method is used to save the student data to database
	 * 
	 * @return Result This method redirects to createAndList.scala.html by calling index() method
	 */
	@Transactional
	public Result saveStudent() {
		Form<Student> studentForm = formFactory.form(Student.class).bindFromRequest();
		if (studentForm.hasErrors()) {
			return badRequest(views.html.addStudent.render(studentForm, departments, sex));
		}
		JPA.em().persist(studentForm.get());
		return redirect(routes.Application.listStudent());
	}

	/**
	 * This method is used to delete student specified by id.
	 * 
	 * @param id This is the student id
	 * 
	 * @return Result This method redirects to createAndList.scala.html by calling index() method
	 */
	@Transactional
	public Result deleteStudent(Long id) {
		Student student = JPA.em().find(Student.class, id);
		JPA.em().remove(student);
		return redirect(routes.Application.listStudent());
	}

	/**
	 * This method is used to get update form of student specified by id.
	 * 
	 * @param id This is the student id
	 * 
	 * @return Result This method redirects to update.scala.html
	 */
	@Transactional(readOnly=true)
	public Result updateStudentGet(Long id) {
		Student student = JPA.em().find(Student.class, id);
		Form<Student> studentForm = Form.form(Student.class).fill(student);
		return ok(views.html.updateStudent.render(id, studentForm, departments, sex));
	}

	/**
	 * This method is used to save update form of student specified by id.
	 * 
	 * @param id This is the student id
	 * 
	 * @return Result This method redirects to createAndList.scala.html
	 */
	@Transactional(readOnly=false)
	public Result updateStudentPost(Long id) {
		Form<Student> studentForm = formFactory.form(Student.class).bindFromRequest();
		if (studentForm.hasErrors()) {
			return badRequest(views.html.updateStudent.render(id, studentForm, departments, sex));
		}
		Student student = studentForm.get();
		Student t = JPA.em().find(Student.class, student.getId());
		t.setName(student.getName());
		t.setAddress(student.getAddress());
		t.setBirthDate(student.getBirthDate());
		t.setDepartment(student.getDepartment());
		t.setGender(student.getGender());
		return redirect(routes.Application.listStudent());
	}	
	
	public Result changeLanguage(String lang) {
		ctx().changeLang(new Lang(Lang.forCode(lang)));
		return redirect(request().getHeader("referer"));
	}
	
}
