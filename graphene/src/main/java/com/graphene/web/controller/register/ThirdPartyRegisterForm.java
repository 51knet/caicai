package com.graphene.web.controller.register;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.graphene.web.util.validators.FieldMatch;



@FieldMatch.List({
    @FieldMatch(first = "psw", second = "confirmpsw", message = "The password fields must match"),
})

public class ThirdPartyRegisterForm {

	@NotEmpty
	@Email
	private String emails;
	@Length(min=3,max=12)
	@NotEmpty
	private String psw;
	@NotEmpty
	private String confirmpsw;
	
	private String userType;
	
	private String thirdParty;
	private String thirdPartyName;
	

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public String getThirdPartyName() {
		return thirdPartyName;
	}

	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}
	
}
