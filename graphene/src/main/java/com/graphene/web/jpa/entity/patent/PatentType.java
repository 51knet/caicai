package com.graphene.web.jpa.entity.patent;

import javax.persistence.Entity;

import com.graphene.web.jpa.entity.AbstractEntity;

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
