package com.knet51.ccweb.jpa.entities.resource;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class ResourceType extends AbstractEntity {
	
	private String typeName;
	private String typeStatus;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeStatus() {
		return typeStatus;
	}
	public void setTypeStatus(String typeStatus) {
		this.typeStatus = typeStatus;
	}
	public ResourceType() {
		super();
	}
	
	
	
}
