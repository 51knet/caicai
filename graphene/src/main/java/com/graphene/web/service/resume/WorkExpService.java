package com.graphene.web.service.resume;

import java.util.List;

import com.graphene.web.jpa.entity.resume.WorkExp;


public interface WorkExpService {
	WorkExp createWorkExp(WorkExp workExp);
	WorkExp updateWorkExp(WorkExp workExp);
	WorkExp findOneById(Long Id);
	void destory(Long id);
	List<WorkExp> findWorkList(Long teacher_id);
}
