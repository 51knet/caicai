package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "educational_background")
public class EduBackground extends AbstractEntity {
	
	private String school;
	
	private String college;
	
	private String degree;
	
	private String major;
	
	private String startTime;
	
	private String endTime;
	@Lob
	private String educationDesc;
	
	private Long teacherid;
	
	private String level; // nursery,primary,middle,high,bachelor,master
	
	private String classNum;
	
	private String teacherNam;

	private String forbidden;
	
	
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getTeacherNam() {
		return teacherNam;
	}
	public void setTeacherNam(String teacherNam) {
		this.teacherNam = teacherNam;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public EduBackground() {
		super();
	}
	
	
	
}
