package com.knet51.diplomat.jpa.services.patent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.patent.PatentField;
import com.knet51.ccweb.jpa.repository.patent.PatentFieldRepository;
import com.knet51.diplomat.jpa.services.patent.PatentFieldService;
@Service("patentFieldService")
public class PatentFieldServiceImpl implements PatentFieldService {

	@Autowired
	private PatentFieldRepository patentFieldRepository;
	@Override
	public List<PatentField> findAll() {
		return patentFieldRepository.findAll();
	}
	@Override
	public PatentField findOne(Long id) {
		return patentFieldRepository.findOne(id);
	}

}
