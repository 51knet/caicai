package com.knet51.courses.controllers;
import org.hibernate.validator.constraints.NotEmpty;
public class CommentInfoForm {
	private Long mark;
	@NotEmpty
	private String commentDesc;
	private String commentDate;
	@NotEmpty
	private String commentTitle;

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
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	
}
