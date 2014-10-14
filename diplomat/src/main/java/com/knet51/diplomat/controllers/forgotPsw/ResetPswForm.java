package com.knet51.diplomat.controllers.forgotPsw;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.knet51.diplomat.util.validators.FieldMatch;




@FieldMatch.List({
    @FieldMatch(first = "psw", second = "confirmpsw", message = "The password fields must match"),
})

public class ResetPswForm {

	@NotEmpty
	@Size(min=3, max=25)
	private String psw;
	@NotEmpty
	@Size(min=3, max=25)
	private String confirmpsw;

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
