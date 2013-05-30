package com.knet51.ccweb.controllers.admin.user.courses;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
public class UserCourseForm {
	private Long mark;
	@NotEmpty
	private String commentDesc;
	private Date commentDate;

	private Long courseid;
	/*@NotEmpty
	private Long userId;
	*/
	public Long getTeachercourseid() {
		return courseid;
	}
	public void setTeachercourseid(Long teachercourseid) {
		this.courseid = teachercourseid;
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
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
}
