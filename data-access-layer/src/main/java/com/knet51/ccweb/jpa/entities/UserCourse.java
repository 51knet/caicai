package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;

@Entity
public class UserCourse extends AbstractEntity {
	private Long user_id;
	private Long course_id;
	private Integer status;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public UserCourse(Long user_id, Long course_id, Integer status) {
		super();
		this.user_id = user_id;
		this.course_id = course_id;
		this.status = status;
	}
	public UserCourse() {
		super();
	}
	
	
}
