package jp.gmo.net.dto;

import jp.gmo.net.service.impl.UserServiceImpl;
import play.data.validation.Constraints;
import play.i18n.Messages;

/**
 * Backing class for the register form.
 */
public class RegisterFormData {

	/** The submitted email. */
	@Constraints.Required(message = "my.required.email")
	@Constraints.Email(message = "my.format.email")
	public String email = "";

	private String username;

	/** The submitted password. */
	@Constraints.Required(message = "my.required.password")
	public String password = "";

	@Constraints.Pattern(value = "\\d{1,11}", message = "my.pattern.phone")
	@Constraints.MinLength(value=10, message="my.minLength.phone")
	private String phone;

	@Constraints.MaxLength(value=200, message="my.maxLength.address")
	private String address;

	@Constraints.Min(value=1, message="my.min.age")
	@Constraints.Max(value=100, message="my.max.age")
	private Long age;
	
	@Constraints.Pattern(value="\\d{4}/\\d{2}/\\d{2}", message="my.pattern.birthday")
	private String birthday;
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the age
	 */
	public Long getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Long age) {
		this.age = age;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String validate() {
		UserServiceImpl userServiceimpl= new UserServiceImpl();
        if (username == null || username.trim().equals("")) {
            return Messages.get("my.required.username");
        }
        return null;
    }

	/**
	 * 
	 */
	public RegisterFormData() {
	}

}
