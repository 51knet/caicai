package com.knet51.courses.jpa.services.requirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.RequirType;
import com.knet51.ccweb.jpa.repository.RequirTypeRepository;

@Service("requirTypeService")
public class RequirTypeServiceImpl implements RequirTypeService{
	@Autowired
	private RequirTypeRepository typeRepository;
	@Override
	public List<RequirType> findTypeList(){
		return typeRepository.findAll();
	}
	@Override
	public RequirType findOne(Long type_id) {
		return typeRepository.findOne(type_id);
	};
}
