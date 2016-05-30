package jp.gmo.net.controllers;

import javax.inject.Inject;

import jp.gmo.net.dto.LoginFormData;
import jp.gmo.net.models.User;
import jp.gmo.net.service.UserService;
import jp.gmo.net.views.html.Detail;
import jp.gmo.net.views.html.Index;
import jp.gmo.net.views.html.Login;
import jp.gmo.net.views.html.Profile;
import jp.gmo.net.views.html.Register1;
import play.cache.CacheApi;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class UserController extends Controller {
	
	private CacheApi cache;
	
	@Inject
	public UserController(CacheApi cache) {
		this.cache = cache;
	}
	
	@Inject
	private UserService userService;
	
	/**
	 * Provides the Index page.
	 * 
	 * @return The Index page.
	 */
	public Result index() {
		return ok(Index.render("Home", Secured.isLoggedIn(ctx()),
				Secured.getUserInfo(ctx())));
	}

	/**
	 * Provides the Login page (only to unauthenticated users).
	 * 
	 * @return The Login page.
	 */
	public Result login() {
		Form<LoginFormData> formData = Form.form(LoginFormData.class);
		return ok(Login.render("Login", Secured.isLoggedIn(ctx()),
				Secured.getUserInfo(ctx()), formData));
	}

	public Result register() {
		userService.insertUser();
		return ok(Register1.render("Register1", Secured.isLoggedIn(ctx()),
				Secured.getUserInfo(ctx())));
	}
	
	/**
	 * Processes a login form submission from an unauthenticated user. First we
	 * bind the HTTP POST data to an instance of LoginFormData. The binding
	 * process will invoke the LoginFormData.validate() method. If errors are
	 * found, re-render the page, displaying the error data. If errors not
	 * found, render the page with the good data.
	 * 
	 * @return The index page with the results of validation.
	 */
	public Result postLogin() {
		// Get the submitted form data from the request object, and run
		// validation.
		Form<LoginFormData> formData = Form.form(LoginFormData.class)
				.bindFromRequest();
		if (formData.hasErrors()) {
			return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()),
					Secured.getUserInfo(ctx()), formData));
		} else {
			User user = userService.getUserInfo(formData.get().email, formData.get().password);
			if (user == null) {
				flash("error", "Login credentials not valid.");
				return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()),
						Secured.getUserInfo(ctx()), formData));
			} else {
				// email/password OK, so now we set the session variable and only go to
				// authenticated pages.
				session().clear();
				session("email", formData.get().email);
				
				if (session().get("email") != null) {
					cache.set(session().get("email"), user);
				}
				return redirect(jp.gmo.net.controllers.routes.UserController.profile());
			}
		}
	}

	/**
	 * Logs out (only for authenticated users) and returns them to the Index
	 * page.
	 * 
	 * @return A redirect to the Index page.
	 */
	@Security.Authenticated(Secured.class)
	public Result logout() {
		cache.remove(session().get("email"));
		session().clear();
		return redirect(jp.gmo.net.controllers.routes.UserController.index());
	}

	/**
	 * Provides the Profile page (only to authenticated users).
	 * 
	 * @return The Profile page.
	 */
	@Security.Authenticated(Secured.class)
	public Result profile() {
		return ok(Profile.render("Profile", Secured.isLoggedIn(ctx()),
				Secured.getUserInfo(ctx())));
	}
	
	/**
	 * Provides the Profile page (only to authenticated users).
	 * 
	 * @return The Profile page.
	 */
	@Security.Authenticated(Secured.class)
	public Result detail() {
		return ok(Detail.render("Detail", Secured.isLoggedIn(ctx()),
				Secured.getUserInfo(ctx())));
	}
}
