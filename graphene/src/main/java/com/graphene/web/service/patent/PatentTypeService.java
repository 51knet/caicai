package com.graphene.web.service.patent;

import java.util.List;

import com.graphene.web.jpa.entity.patent.PatentType;


public interface PatentTypeService {
	List<PatentType> findAllPatentType();
	
	PatentType findOne(Long id);
}
