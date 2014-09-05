package com.knet51.ccweb.jpa.entities.consult;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;

@Entity
public class ConsultOrder extends AbstractEntity {
	private String phone;
	private String email;
	private String title;
	@Lob
	private String content;
	
	private String username;
	private String date;
	
	
	@ManyToOne
	private User teacher;
	
	@ManyToOne
	private User consulter;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public User getConsulter() {
		return consulter;
	}

	public void setConsulter(User consulter) {
		this.consulter = consulter;
	}


	public ConsultOrder() {
		super();
	}
	
}
