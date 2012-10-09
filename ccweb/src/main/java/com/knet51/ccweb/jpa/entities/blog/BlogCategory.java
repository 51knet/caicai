package com.knet51.ccweb.jpa.entities.blog;

import javax.persistence.Entity;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class BlogCategory extends AbstractEntity {

	private String name;
	
	public BlogCategory() {
	}
	
	public BlogCategory(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}