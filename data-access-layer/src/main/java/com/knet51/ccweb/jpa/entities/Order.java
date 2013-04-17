package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

@Entity
@Table(name = "UserOrder")
public class Order extends AbstractEntity{

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private TeacherCourse course;
	
	private Date startTime;
	private Date endTime;
	private String status;
	@Lob
	private String description;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TeacherCourse getCourse() {
		return course;
	}
	public void setCourse(TeacherCourse course) {
		this.course = course;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
