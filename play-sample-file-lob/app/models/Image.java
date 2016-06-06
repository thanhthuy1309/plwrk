package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class Image {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] content;
    private String contentType;

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public static Image byId(Long id) {
        return null;
    }

    public static List<Image> all() {
        return null;
    }

    public static List<Long> allIds() {
        List<Long> ids = new ArrayList<Long>();

        for (Image image : all()) {
            ids.add(image.id);
        }

        return ids;
    }
}
