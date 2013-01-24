package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;

@Entity
public class CourseResource extends AbstractEntity {
	
	private String fileName;
	private String saveName;
	private String savePath;
	@Lob
	@Column(length=10000)
	private String resourceDesc;
	private String lessonNum; //lesson 0ne,lesson two and so on....
	private String date;
	private Long course_id;
	private Long courseLessonId;
	@ManyToOne
	private ResourceType resourceType;
	@ManyToOne
	private User user;
	
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

	public Long getCourseLessonId() {
		return courseLessonId;
	}
	public void setCourseLessonId(Long courseLessonId) {
		this.courseLessonId = courseLessonId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public String getLessonNum() {
		return lessonNum;
	}
	public void setLessonNum(String lessonNum) {
		this.lessonNum = lessonNum;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	public CourseResource() {
		super();
	}
	
	
	
}
