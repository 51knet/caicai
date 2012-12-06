package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Student {
	@Id
	private Long id; /* manually set id to user's id */
	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	@PrimaryKeyJoinColumn(name = "id")
	private User user;

	private String role;
	private String college;
	private String junior_high_school;
	private String senior_high_school;
	private String primary_school;
	protected Student() {

	}
	public Student(User user) {
		this.user = user;
		setId(user.getId());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getJunior_high_school() {
		return junior_high_school;
	}

	public void setJunior_high_school(String junior_high_school) {
		this.junior_high_school = junior_high_school;
	}

	public String getSenior_high_school() {
		return senior_high_school;
	}

	public void setSenior_high_school(String senior_high_school) {
		this.senior_high_school = senior_high_school;
	}

	public String getPrimary_school() {
		return primary_school;
	}

	public void setPrimary_school(String primary_school) {
		this.primary_school = primary_school;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", user=" + user + ", role=" + role
				+ ", college=" + college + ", junior_high_school="
				+ junior_high_school + ", senior_high_school="
				+ senior_high_school + ", primary_school=" + primary_school
				+ "]";
	}
}