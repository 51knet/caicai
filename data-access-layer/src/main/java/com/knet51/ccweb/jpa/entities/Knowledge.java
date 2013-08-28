package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;

@Entity
public class Knowledge extends AbstractEntity {
	
	private Long userid;
	
	private Long courseid;
	
	private String status;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getCourseid() {
		return courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Knowledge() {
		super();
	}
	
	
}
