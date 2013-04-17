package com.knet51.ccweb.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.courses.CourseType;
@Transactional
public interface CourseTypeRepository  extends JpaRepository<CourseType, Long>, JpaSpecificationExecutor<CourseType>{
	
	
}
