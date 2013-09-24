package com.knet51.ccweb.beans;

import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.timeline.Trends;

public class CommentBeans {
	private Trends trends;
	private Comment comment;
	private Comment hostComment;
	
	public Comment getHostComment() {
		return hostComment;
	}
	public void setHostComment(Comment hostComment) {
		this.hostComment = hostComment;
	}
	public Trends getTrends() {
		return trends;
	}
	public void setTrends(Trends trends) {
		this.trends = trends;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public CommentBeans() {
		super();
	}
	
	
}
