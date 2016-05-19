package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.data.validation.ValidationError;
import play.i18n.Messages;

@Entity
@Table(name = "student_tb")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	
	private String name;

	private String address;

	private String gender;

	private String birthDate;
	private String department;

	@Version
	private Timestamp lastUpdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public List<ValidationError> validate() {
	    List<ValidationError> errors = new ArrayList<ValidationError>();
	    if (this.name.equals("")) {
	        errors.add(new ValidationError("name", Messages.get("actor.name.required", "name")));
	    }
	    if (this.address.equals("")) {
	        errors.add(new ValidationError("address", Messages.get("actor.name.required", "address")));
	    }
	    if (this.birthDate.equals("")) {
	        errors.add(new ValidationError("birthDate", Messages.get("actor.name.required", "birthDate")));
	    }
	    return errors.isEmpty() ? null : errors;
	}
}
