package com.knet51.courses.jpa.services.patent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.ccweb.jpa.repository.patent.PatentTypeRepository;

@Service("patentTypeService")
public class PatentTypeServiceImpl implements PatentTypeService {
	@Autowired
	private PatentTypeRepository typeRepository;

	@Override
	public List<PatentType> findAllPatentType() {
		return typeRepository.findAll();
	}

	@Override
	public PatentType findOne(Long id) {
		return typeRepository.findOne(id);
	}

}
