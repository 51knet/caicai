package com.knet51.ccweb.jpa.entities.courses;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;
@Entity
public class CourseType extends AbstractEntity {
	private String typeName;
	@ManyToOne
	private User user;

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
