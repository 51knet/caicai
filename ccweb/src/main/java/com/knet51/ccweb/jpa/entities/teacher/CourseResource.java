package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
@Entity
public class CourseResource extends AbstractEntity {
	
	private String fileName;
	private String savePath;
	private Long techer_id;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public Long getTecher_id() {
		return techer_id;
	}
	public void setTecher_id(Long techer_id) {
		this.techer_id = techer_id;
	}
	
}
