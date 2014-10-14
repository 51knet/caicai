package com.knet51.diplomat.jpa.services.resume;

import java.util.List;

import com.knet51.ccweb.jpa.entities.EduBackground;

public interface EduBackgroundService {
	EduBackground createEduBackground(EduBackground eduBackground);
	EduBackground findOneById(Long Id);
	EduBackground updateEduBackground(EduBackground eduBackground);
	void destory(Long id);
	List<EduBackground> findEduListByTeacherId(Long teacher_id);
}
