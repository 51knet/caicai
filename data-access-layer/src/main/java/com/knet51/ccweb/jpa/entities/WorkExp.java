package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="work_experience")
public class WorkExp extends AbstractEntity {
	private String company;
	private String department;
	private String position;
	private String startTime;
	private String endTime;
	private Long teacherid;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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

	public Long getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Long teacherid) {
		this.teacherid = teacherid;
	}
	public WorkExp() {
		super();
	}
	
	
}
