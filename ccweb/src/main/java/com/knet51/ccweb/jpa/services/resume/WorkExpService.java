package com.knet51.ccweb.jpa.services.resume;

import java.util.List;

import com.knet51.ccweb.jpa.entities.WorkExp;

public interface WorkExpService {
	WorkExp createWorkExp(WorkExp workExp);
	WorkExp updateWorkExp(WorkExp workExp);
	WorkExp findOneById(Long Id);
	void destory(Long id);
	List<WorkExp> findWorkList(Long teacher_id);
}
