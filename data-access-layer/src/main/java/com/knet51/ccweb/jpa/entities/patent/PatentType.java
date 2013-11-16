package com.knet51.ccweb.jpa.entities.patent;

import javax.persistence.Entity;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
@Entity
public class PatentType extends AbstractEntity {
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public PatentType() {
		super();
	}
	
	
}
