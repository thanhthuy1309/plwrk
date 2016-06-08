package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Image;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	@Transactional(readOnly = true)
    public Result index() {
        return ok(index.render(allIds(), ""));
    }
    
    @Transactional(readOnly = true)
    public Result renderImage(Long id) {
    	Image image = JPA.em().find(Image.class, id);
    	return ok(image.getContent()).as(image.getContentType());
    }
    
    @Transactional(readOnly = false)
    public Result upload() {
    	Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String s = null;
        s.split("s");
        if (!picture.getFilename().equals("")) {
			File file = (File) picture.getFile();
			try {
				Image image = new Image();
				image.setContent(com.google.common.io.Files.toByteArray(file));
				image.setContentType(picture.getContentType());
				JPA.em().persist(image);
			} catch (IOException e) {
				return badRequest();
			}
			return ok(index.render(allIds(), "File uploaded"));
		}
    	return badRequest(index.render(allIds(), "No file uploaded"));
    }

    public List<Long> allIds() {
        List<Long> ids = new ArrayList<Long>();
        List<Image> images = (List<Image>) JPA.em().createQuery("select i from Image i").getResultList();
        
        for (Image image : images) {
            ids.add(image.getId());
        }
        return ids;
    }
}
