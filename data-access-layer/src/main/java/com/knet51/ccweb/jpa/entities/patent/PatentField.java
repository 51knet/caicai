package com.knet51.ccweb.jpa.entities.patent;

import javax.persistence.Entity;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
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
