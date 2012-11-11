package com.knet51.ccweb.jpa.entities.blog;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
@JsonIgnoreProperties(value={"author"})
public class BlogCategory extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher author;
	
	@NotEmpty
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

	public Teacher getAuthor() {
		return author;
	}

	public void setAuthor(Teacher author) {
		this.author = author;
	}
}