package com.knet51.ccweb.jpa.services.patent;

import java.util.List;

import com.knet51.ccweb.jpa.entities.patent.PatentField;

public interface PatentFieldService {
	List<PatentField> findAll();
	
	PatentField findOne(Long id);
}
