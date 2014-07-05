package com.graphene.web.service.resume;

import java.util.List;

import com.graphene.web.jpa.entity.resume.EduBackground;


public interface EduBackgroundService {
	EduBackground createEduBackground(EduBackground eduBackground);
	EduBackground findOneById(Long Id);
	EduBackground updateEduBackground(EduBackground eduBackground);
	void destory(Long id);
	List<EduBackground> findEduListByTeacherId(Long teacher_id);
}
