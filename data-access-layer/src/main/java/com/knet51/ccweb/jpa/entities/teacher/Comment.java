package com.knet51.ccweb.jpa.entities.teacher;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
@Entity
@Table(name="comment")
public class Comment extends AbstractEntity {
	private Long mark;
	@Column(length=1000)
	private String commentDesc;
	private String commentDate;
	private String commentTitle;

	private Long userid;
	private Long courseid;
	
	
	
	
	public Long getCourseid() {
		return courseid;
	}
	public void setCourseid(Long courseid) {
		this.courseid = courseid;
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
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	
	
	
	
	
}
