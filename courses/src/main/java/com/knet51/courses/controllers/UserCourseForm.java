package com.knet51.courses.controllers;
import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;
public class UserCourseForm {
	private Long mark;
	@NotEmpty
	@Max(value = 10000, message="overflow 10000!!")
	private String commentDesc;
	private String commentDate;

	private Long teachercourseid;
	/*@NotEmpty
	private Long userId;
	*/
	public Long getTeachercourseid() {
		return teachercourseid;
	}
	public void setTeachercourseid(Long teachercourseid) {
		this.teachercourseid = teachercourseid;
	}
	/*public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}*/
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
	
}
