package com.graphene.web.controller.admin.applyright;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class ParkRightForm {
	@NotEmpty
	private String parkBoss;
	@Size(max=13, min=1)
	private String parkPhone;
	@NotEmpty
	@Email
	private String parkEmail;
	@NotEmpty
	private String parkName;
	private String parkContent;

	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getParkBoss() {
		return parkBoss;
	}
	public void setParkBoss(String parkBoss) {
		this.parkBoss = parkBoss;
	}
	public String getParkPhone() {
		return parkPhone;
	}
	public void setParkPhone(String parkPhone) {
		this.parkPhone = parkPhone;
	}
	public String getParkEmail() {
		return parkEmail;
	}
	public void setParkEmail(String parkEmail) {
		this.parkEmail = parkEmail;
	}
	public String getParkContent() {
		return parkContent;
	}
	public void setParkContent(String parkContent) {
		this.parkContent = parkContent;
	}
	public ParkRightForm() {
		super();
	}
	
}
