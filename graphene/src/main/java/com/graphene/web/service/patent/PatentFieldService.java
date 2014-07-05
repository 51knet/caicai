package com.graphene.web.service.patent;

import java.util.List;

import com.graphene.web.jpa.entity.patent.PatentField;


public interface PatentFieldService {
	List<PatentField> findAll();
	
	PatentField findOne(Long id);
}
