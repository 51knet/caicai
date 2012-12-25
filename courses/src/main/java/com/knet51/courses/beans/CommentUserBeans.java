package com.knet51.courses.beans;

import com.knet51.ccweb.jpa.entities.teacher.Comment;

public class CommentUserBeans {
	private String userName;
	private String photoUrl;
	private Comment comment;
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public CommentUserBeans(String userName,String photoUrl,Comment comment) {
		super();
		this.userName=userName;
		this.photoUrl=photoUrl;
		this.comment=comment;
	}
	public CommentUserBeans() {
		super();
	}
}
