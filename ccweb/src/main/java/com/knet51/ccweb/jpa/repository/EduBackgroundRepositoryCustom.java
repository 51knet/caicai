package com.knet51.ccweb.jpa.repository;

import java.util.List;

import com.knet51.ccweb.jpa.entities.EduBackground;

public interface EduBackgroundRepositoryCustom {

	EduBackground findOneByTeacherId(Long teacher_id);
	
	List<EduBackground> findEduList(Long teacher_id);
}
