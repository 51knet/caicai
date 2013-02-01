package com.knet51.courses.controllers.signup;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.knet51.courses.util.validators.FieldMatch;


@FieldMatch.List({
    @FieldMatch(first = "psw", second = "confirmpsw", message = "The password fields must match"),
})

public class SignupForm {

	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@Size(min=3, max=25)
	private String psw;
	@NotEmpty
	@Size(min=3, max=25)
	private String confirmpsw;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String password) {
		this.psw = password;
	}

	public String getConfirmpsw() {
		return confirmpsw;
	}

	public void setConfirmpsw(String confirmpsw) {
		this.confirmpsw = confirmpsw;
	}
}
