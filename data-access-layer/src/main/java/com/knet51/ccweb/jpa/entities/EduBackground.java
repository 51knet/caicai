package com.knet51.ccweb.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "educational_background")
public class EduBackground extends AbstractEntity {
	
	private String school;
	
	private String college;
	
	private String degree;
	
	private String startTime;
	
	private String endTime;
	@Lob
	@Column(length=10000)
	private String educationDesc;
	
	private Long teacherid;


	public Long getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Long teacherid) {
		this.teacherid = teacherid;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
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
	public String getEducationDesc() {
		return educationDesc;
	}
	public void setEducationDesc(String educationDesc) {
		this.educationDesc = educationDesc;
	}
	public EduBackground() {
		super();
	}
	
	
	
}
