package com.knet51.courses.controllers.teacherCourse;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public class CommentInfoForm {
	@NotEmpty
	private Long mark;
	@NotEmpty
	private String commentDesc;
	@NotEmpty
	private String commentDate;
	@NotEmpty
	private String commentTitle;
	@ManyToOne
	@JoinColumn(name="user_Id")
	private User user;
	@ManyToOne
	@JoinColumn(name="teacherCourse_Id")
	private TeacherCourse teacherCourse;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}
	public void setTeacherCourse(TeacherCourse teacherCourse) {
		this.teacherCourse = teacherCourse;
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
