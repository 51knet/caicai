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
	
	@OneToMany(mappedBy="resourceType",fetch=FetchType.EAGER)
	private Set<Resource> resource = new HashSet<Resource>();
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public ResourceType(String typeName) {
		super();
		this.typeName = typeName;
	}

	public ResourceType() {
		super();
	}

	public Set<Resource> getResource() {
		return resource;
	}

	public void setResource(Set<Resource> resource) {
		this.resource = resource;
	}
	
	
}
