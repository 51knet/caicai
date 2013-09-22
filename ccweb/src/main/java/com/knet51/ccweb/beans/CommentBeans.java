package com.knet51.ccweb.beans;

import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.timeline.Trends;

public class CommentBeans {
	private Trends trends;
	private Comment comment;
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
