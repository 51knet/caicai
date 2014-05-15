package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.knet51.ccweb.jpa.entities.projects.Projects;

@Entity
@Table(name = "userOrder")
public class UserOrder extends AbstractEntity{
	
	private String courseId;
	private Integer count;
	
	private Date startTime;
	private Date endTime;
	private String status;
	@Lob
	private String description;
	@ManyToOne
//	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne
	private Projects project;
	
	public UserOrder(User user, String courseId) {
		this.user = user;
		this.courseId = courseId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
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
	
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public UserOrder() {
		super();
	}
	public Projects getProject() {
		return project;
	}
	public void setProject(Projects project) {
		this.project = project;
	}
	
	

	
	
}
