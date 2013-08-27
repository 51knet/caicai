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
	private String varietyDescription;
	private static Map<String, String> varietyMap = new HashMap<String, String>();

	public Trends getTrend() {
		return trend;
	}
	public void setTrend(Trends trend) {
		this.trend = trend;
		this.varietyDescription = parseVariety(trend);
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
	public String getVarietyDescription() {
		return varietyDescription;
	}
	public void setVarityDescription(String varietyDescription) {
		this.varietyDescription = varietyDescription;
	}
	public TrendsBeans() {
		super();
		varietyMap.put("announcement", "一篇公告");
		varietyMap.put("resource", "一份资源");
		varietyMap.put("course", "一节课程");
		varietyMap.put("shuoshuo", "一个说说");
	}
	
	private String parseVariety(Trends trend){
		String variety = trend.getVariety();
		return varietyMap.get(variety);
	}
	
}
