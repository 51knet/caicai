package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class CourseResource extends AbstractEntity {
	
	private String fileName;
	private String saveName;
	private String savePath;
	@Lob
	@Column(length=10000)
	private String resourceDesc;
	private String courseOrder; //lesson 0ne,lesson two and so on....
	private String date;
	private Long course_id;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	
	public String getResourceDesc() {
		return resourceDesc;
	}
	
	
	
	public String getCourseOrder() {
		return courseOrder;
	}
	public void setCourseOrder(String courseOrder) {
		this.courseOrder = courseOrder;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	public CourseResource() {
		super();
	}
	
	
	
}
