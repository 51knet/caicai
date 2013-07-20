package com.knet51.ccweb.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.timeline.Trends;

public class TrendsBeans {
	private Trends trend;
	private List<Comment> commentList;
	private Long commentCount;
	private String varityDescription;
	private static Map<String, String> varityMap = new HashMap<String, String>();

	public Trends getTrend() {
		return trend;
	}
	public void setTrend(Trends trend) {
		this.trend = trend;
		this.varityDescription = parseVarity(trend);
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
	public String getVarityDescription() {
		return varityDescription;
	}
	public void setVarityDescription(String varityDescription) {
		this.varityDescription = varityDescription;
	}
	public TrendsBeans() {
		super();
		varityMap.put("announcement", "一篇公告");
		varityMap.put("resource", "一份资源");
		varityMap.put("course", "一节课程");
	}
	
	private String parseVarity(Trends trend){
		String varity = trend.getVariety();
		return varityMap.get(varity);
	}
	
}
