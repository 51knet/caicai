package com.knet51.ccweb.beans;

import java.util.List;

import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.timeline.Trends;

public class TrendsBeans {
	private Trends trend;
	private List<Comment> commentList;
	private Long commentCount;

	public Trends getTrend() {
		return trend;
	}
	public void setTrend(Trends trend) {
		this.trend = trend;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public Long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	public TrendsBeans() {
		super();
	}
	
}
