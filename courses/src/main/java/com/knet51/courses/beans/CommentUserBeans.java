package com.knet51.courses.beans;

public class CommentUserBeans {
	private String name;
	private String commentTitle;
	private String commentDate;
	private String commentDesc;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentDesc() {
		return commentDesc;
	}
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}
	public CommentUserBeans(String name,String commentTitle,String commentDate,String commentDesc) {
		super();
		this.name=name;
		this.commentTitle=commentTitle;
		this.commentDesc=commentDesc;
		this.commentDate=commentDate;
	}
	public CommentUserBeans() {
		super();
	}
}
