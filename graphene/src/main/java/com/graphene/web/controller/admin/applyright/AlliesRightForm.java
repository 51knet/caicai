package com.graphene.web.controller.admin.applyright;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AlliesRightForm {
	private String alliesName;
	@NotEmpty
	private String alliesBoss;
	@Size(max=13, min=1)
	private String alliesPhone;
	@NotEmpty
	@Email
	private String alliesEmail;
	private String alliesContent;
	
	public String getAlliesName() {
		return alliesName;
	}

	public void setAlliesName(String alliesName) {
		this.alliesName = alliesName;
	}

	public String getAlliesBoss() {
		return alliesBoss;
	}

	public void setAlliesBoss(String alliesBoss) {
		this.alliesBoss = alliesBoss;
	}

	public String getAlliesPhone() {
		return alliesPhone;
	}

	public void setAlliesPhone(String alliesPhone) {
		this.alliesPhone = alliesPhone;
	}

	public String getAlliesEmail() {
		return alliesEmail;
	}

	public void setAlliesEmail(String alliesEmail) {
		this.alliesEmail = alliesEmail;
	}

	public String getAlliesContent() {
		return alliesContent;
	}

	public void setAlliesContent(String alliesContent) {
		this.alliesContent = alliesContent;
	}

	public AlliesRightForm() {
		super();
	}
	
	
}
