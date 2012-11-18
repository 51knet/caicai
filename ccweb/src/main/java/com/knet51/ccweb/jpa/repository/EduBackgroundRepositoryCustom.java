package com.knet51.ccweb.jpa.repository;

import com.knet51.ccweb.jpa.entities.EduBackground;

public interface EduBackgroundRepositoryCustom {
	
	
	EduBackground findOneByTeacherId(Long teacher_id);
}
