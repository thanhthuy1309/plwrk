package controllers;

import javax.inject.Inject;

import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	
	
    @Inject
    FormFactory formFactory;
    
    public Result index() {
        return ok(index.render());
    }
}
