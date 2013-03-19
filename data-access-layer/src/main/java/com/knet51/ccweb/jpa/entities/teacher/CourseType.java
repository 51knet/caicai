package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
@Entity
public class CourseType extends AbstractEntity {
	private String typeName;

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public CourseType() {
		super();
	}
}
