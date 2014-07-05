package com.graphene.web.jpa.entity.patent;

import javax.persistence.Entity;

import com.graphene.web.jpa.entity.AbstractEntity;

@Entity
public class PatentField  extends AbstractEntity{
	
	private String fieldName;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public PatentField() {
		super();
	}
	
	
}
