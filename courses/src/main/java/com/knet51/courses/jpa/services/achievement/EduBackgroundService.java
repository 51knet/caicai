package com.knet51.courses.jpa.services.achievement;

import java.util.List;

import com.knet51.ccweb.jpa.entities.EduBackground;

public interface EduBackgroundService {
	EduBackground createEduBackground(EduBackground eduBackground);
	EduBackground findOneById(Long Id);
	EduBackground updateEduBackground(EduBackground eduBackground);
	void destory(Long id);
	List<EduBackground> findEduListByTeacherId(Long teacher_id);
}
