package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;

@Entity
public class RequirType extends AbstractEntity {
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public RequirType() {
		super();
	}
	
	
}
