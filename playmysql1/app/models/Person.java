package models;

import javax.persistence.*;

@Entity
@Table(name = "person_tb")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

    private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
