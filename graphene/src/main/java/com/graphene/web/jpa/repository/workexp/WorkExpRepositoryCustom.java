package com.graphene.web.jpa.repository.workexp;

import java.util.List;

import com.graphene.web.jpa.entity.resume.WorkExp;


public interface WorkExpRepositoryCustom {
	
	List<WorkExp> findWorkExpList(Long teacher_id);
}
