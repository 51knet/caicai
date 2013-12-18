package com.knet51.patents.jpa.services.patent;

import java.util.List;

import com.knet51.ccweb.jpa.entities.patent.PatentType;

public interface PatentTypeService {
	List<PatentType> findAllPatentType();
	
	PatentType findOne(Long id);
}
