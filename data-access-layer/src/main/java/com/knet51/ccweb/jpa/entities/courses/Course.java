package com.knet51.ccweb.jpa.entities.courses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;

@Entity
public class Course extends AbstractEntity {
	
	private String courseName;
	@Lob
	private String courseDesc;
	private String courseDate;
	private String courseCover;
	private String courseType; 
	@Lob
	private String courseCharacter;
	@Lob
	private String targetPerson;
	@Column(columnDefinition="int default 1")
	private Integer status;  // 1:just show the course in ccweb; 2: both.
	@Column(columnDefinition="int default 1")
	private Integer publish; // 0:the course has been deleted; 1:just show it in teacher admin page; 2:show it in the admin page and front page.
	private String pwd;      // Check the pwd when someone want to check the course.
	
	private Long price;
	
	private String forbidden;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private CourseType cType;
	
	public CourseType getcType() {
		return cType;
	}
	public void setcType(CourseType cType) {
		this.cType = cType;
	}
	
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCourseDate() {
		return courseDate;
	}
	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}
	
	public String getCourseCover() {
		return courseCover;
	}
	public void setCourseCover(String courseCover) {
		this.courseCover = courseCover;
	}
	
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPublish() {
		return publish;
	}
	public void setPublish(Integer publish) {
		this.publish = publish;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Course() {
		super();
	}
	
	public String getCourseCharacter() {
		return courseCharacter;
	}
	public void setCourseCharacter(String courseCharacter) {
		this.courseCharacter = courseCharacter;
	}
	public String getTargetPerson() {
		return targetPerson;
	}
	public void setTargetPerson(String targetPerson) {
		this.targetPerson = targetPerson;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	
}
