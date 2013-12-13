package com.knet51.ccweb.jpa.services.enterprise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Enterprise;
import com.knet51.ccweb.jpa.repository.EnterpriseRepository;

@Transactional
@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private EnterpriseRepository enterpriseRepository;

	@Override
	public Enterprise findOne(Long id) {
		return enterpriseRepository.findOne(id);
	}

	@Override
	public Enterprise createEnterprise(Enterprise usr) {
		return enterpriseRepository.save(usr);
	}

	@Override
	public Enterprise updateEnterprise(Enterprise usr) {
		return enterpriseRepository.save(usr);
	}

}
