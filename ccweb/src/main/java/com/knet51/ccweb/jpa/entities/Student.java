package com.knet51.ccweb.jpa.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Student {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
//	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="user_id")
//	private User user; // identify the basic user information
//	//Student FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`)


	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", user=" + user + ", email=" + email
				+ ", role=" + role + ", college=" + college
				+ ", junior_high_school=" + junior_high_school
				+ ", senior_high_school=" + senior_high_school
				+ ", primary_school=" + primary_school + "]";
	}
	public void setId(Long id) {
		this.id = id;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="id") 
	private User user;
	
	private String email;
	private Integer role;
	private String college;
	private String junior_high_school;
	private String senior_high_school;
	private String primary_school;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
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
}