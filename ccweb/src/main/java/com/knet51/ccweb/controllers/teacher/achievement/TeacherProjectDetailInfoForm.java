package com.knet51.ccweb.controllers.teacher.achievement;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherProjectDetailInfoForm {
	@NotEmpty
	private String projectTitle;
	@NotEmpty
	private String projectSource;
	@NotEmpty
	private String projectStartTime;
	@NotEmpty
	private String projectEndTime;
	@NotEmpty
	@Max(value = 10000, message="overflow 10000!!")
	private String projectDesc;
	
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	
	public String getProjectSource() {
		return projectSource;
	}
	public void setProjectSource(String projectSource) {
		this.projectSource = projectSource;
	}
	public String getProjectStartTime() {
		return projectStartTime;
	}
	public void setProjectStartTime(String projectStartTime) {
		this.projectStartTime = projectStartTime;
	}
	public String getProjectEndTime() {
		return projectEndTime;
	}
	public void setProjectEndTime(String projectEndTime) {
		this.projectEndTime = projectEndTime;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public TeacherProjectDetailInfoForm() {
		super();
	}
	
	
	

}
