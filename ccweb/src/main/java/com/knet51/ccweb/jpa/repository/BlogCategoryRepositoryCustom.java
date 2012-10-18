package com.knet51.ccweb.jpa.repository;

import org.springframework.data.jpa.repository.Query;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;


public interface BlogCategoryRepositoryCustom {
	//TODO: fix belongs to relation
	@Query("select DISTINCT b FROM Teacher t, BlogCategory b where b.name = ?1") 
	BlogCategory findByNameAndTeacher(String name, Teacher teacher);
}
