package com.knet51.ccweb.jpa.entities.courses;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;
@Entity
@Table(name="usercourse")
public class UserCourse extends AbstractEntity {
	private Long mark;
	private String commentDesc;
	private Date commentDate;
	private Long userid;
	private Long teachercourseid;
	public Long getTeachercourseid() {
		return teachercourseid;
	}
	public void setTeachercourseid(Long teachercourseid) {
		this.teachercourseid = teachercourseid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getMark() {
		return mark;
	}
	public void setMark(Long mark) {
		this.mark = mark;
	}
	public String getCommentDesc() {
		return commentDesc;
	}
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}
	
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public UserCourse(){
		super();
	}
	
	
	
	
}
