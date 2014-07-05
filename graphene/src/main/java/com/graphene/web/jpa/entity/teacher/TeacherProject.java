package com.graphene.web.jpa.entity.teacher;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import com.graphene.web.jpa.entity.AbstractEntity;
import com.graphene.web.jpa.entity.user.User;


@Entity
public class TeacherProject extends AbstractEntity {
	@Expose
	private String title;
	@Expose
	private String source;
	@Expose
	private String startTime;
	@Expose
	private String endTime;
	@Expose
	@Lob
	private String detailDesc;
	@ManyToOne
	private User user;
	
	
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDesc() {
		return detailDesc;
	}
	public void setDesc(String projectDesc) {
		this.detailDesc = projectDesc;
	}
	public TeacherProject() {
		super();
	}
	
}
