package controllers;

import static play.libs.Json.toJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

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
import dto.StudentDto;

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
    public Result listStudent(int page) {
    	String sql = "select s from Student s";
    	
    	StudentDto s = new StudentDto();
    	Form<StudentDto> studentForm = Form.form(StudentDto.class).fill(s);

    	return init(page, sql, studentForm, null);
    }

    @Transactional(readOnly = true)
    public Result searchStudent() {
    	Form<StudentDto> studentForm = formFactory.form(StudentDto.class).bindFromRequest();
    	StudentDto student = studentForm.get();
    	Map<String, String> mapValue = new HashMap<String, String>();
    	String sql = "select s from Student s where 1 = 1";
    	if (!isEmpty(student.getName())) {
    		sql += " and name like :name";
    		mapValue.put("name", student.getName());
    	}
    	if (!isEmpty(student.getAddress())) {
    		sql += " and address like :address";
    		mapValue.put("address", student.getAddress());
		}
    	if (!isEmpty(student.getGender()) && !student.getGender().equals("All")) {
    		sql += " and gender like :gender";
    		mapValue.put("gender", student.getGender());
    	}
    	if (!isEmpty(student.getBirthDate())) {
    		sql += " and birthDate like :birthDate";
    		mapValue.put("birthDate", student.getBirthDate());
    	}
    	if (!isEmpty(student.getDepartment())) {
    		sql += " and department like :department";
    		mapValue.put("department", student.getDepartment());
    	}
    	return init(1, sql, studentForm, mapValue);
    }

    private Result init(int page, String sql, Form<StudentDto> studentForm, Map<String, String> mapValue) {
    	int pageSize = 3;
    	Query query = JPA.em().createQuery(sql);
    	if (mapValue != null) {
    		for (String key : mapValue.keySet()) {
                query.setParameter(key,mapValue.get(key));
            }
		}
    	int allResult = query.getResultList().size();
    	int maxPage = calPageSize(allResult, pageSize);
    	
    	int currentRecord = 0;
    	if (page != 1) {
    		currentRecord = pageSize * (page-1);
		}
    	
    	List<Student> students = (List<Student>) query.setFirstResult(currentRecord).setMaxResults(pageSize).getResultList();

    	List<String> sexList = new ArrayList<String>();
    	sexList.add("All");
    	sexList.add("Male");
    	sexList.add("Female");
    	return ok(views.html.listStudent.render(students, studentForm, departments, sexList, page, maxPage));
    }

    private boolean isEmpty(String value) {
		return value == null || value.equals("");
	}

    private int calPageSize(int countList, int viewCount) {

		int pageSize = 0;
		if (countList % viewCount == 0) {
			pageSize = countList / viewCount;
		} else {
			pageSize = countList / viewCount + 1;
		}
		return pageSize;
	}

	@Transactional(readOnly = true)
    public Result students() {
    	List<Student> students = (List<Student>) JPA.em().createQuery("select s from Student s").getResultList();
        return ok(toJson(students));
    }

    public Result addStudentGet() {
    	Student s = new Student();
    	Form<Student> studentForm = Form.form(Student.class).fill(s);
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
		return redirect(routes.Application.listStudent(1));
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
		return redirect(routes.Application.listStudent(1));
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
		return redirect(routes.Application.listStudent(1));
	}	
	
	public Result changeLanguage(String lang) {
		ctx().changeLang(new Lang(Lang.forCode(lang)));
		return redirect(request().getHeader("referer"));
	}
	
}
