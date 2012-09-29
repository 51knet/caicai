package com.knet51.ccweb.jpa.entities.blog;

import javax.persistence.Entity;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class BlogCategory extends AbstractEntity {

	private String name;

	public BlogCategory(String name) {
		super();
		this.name = name;
	}
}