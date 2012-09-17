package com.knet51.ccweb.controllers.user;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDetailInfoForm {

	@NotEmpty
	private String name;
	@NotEmpty
	private String nickName;
	@NotEmpty
	private String gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
