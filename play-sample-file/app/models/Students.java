package models;

import javax.persistence.*;

@Entity
@Table(name = "Students")
public class Students {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String name;
    
    public Students() {
	}

	public Students(Students p) {
		this.id = p.id;
		this.name = p.name;
	}

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
