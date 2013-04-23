package com.knet51.ccweb.controllers.admin.teacher.achievement;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherProjectDetailInfoForm {
	//@NotEmpty
	//@Size(max=25)
	private String projectTitle;
	//@NotEmpty
	//@Size(max=25)
	private String projectSource;
	//@NotEmpty
	//@Size(max=25)
	private String projectStartTime;
	//@NotEmpty
	//@Size(max=25)
	private String projectEndTime;
	@NotEmpty
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
