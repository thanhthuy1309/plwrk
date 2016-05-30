package jp.gmo.net.dto;

import play.data.validation.ValidationError;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing class for the login form.
 */
public class LoginFormData {

	/** The submitted email. */
	public String email = "";
	/** The submitted password. */
	public String password = "";

	/** Required for form instantiation. */
	public LoginFormData() {
	}

	/**
	 * Validates Form<LoginFormData>. Called automatically in the controller by
	 * bindFromRequest(). Checks to see that email and password are valid
	 * credentials.
	 * 
	 * @return Null if valid, or a List[ValidationError] if problems found.
	 */
	public List<ValidationError> validate() {

		List<ValidationError> errors = new ArrayList<>();

		if (email.equals("")) {
			errors.add(new ValidationError("email", "email required"));
		}

		if (password.equals("")) {
			errors.add(new ValidationError("password", "password required"));
		}

		if (errors.size() > 0) {
			return errors;
		}

		return null;
	}

}
