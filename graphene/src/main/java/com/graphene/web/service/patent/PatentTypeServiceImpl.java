package com.graphene.web.service.patent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphene.web.jpa.entity.patent.PatentType;
import com.graphene.web.jpa.repository.patent.PatentTypeRepository;



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
