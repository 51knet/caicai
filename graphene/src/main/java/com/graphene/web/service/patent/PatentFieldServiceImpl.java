package com.graphene.web.service.patent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphene.web.jpa.entity.patent.PatentField;
import com.graphene.web.jpa.repository.patent.PatentFieldRepository;

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
