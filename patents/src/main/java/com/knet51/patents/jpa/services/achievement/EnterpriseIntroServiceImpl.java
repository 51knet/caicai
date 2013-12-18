package com.knet51.patents.jpa.services.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Enterprise;
import com.knet51.ccweb.jpa.entities.enterprise.EnterpriseIntro;
import com.knet51.ccweb.jpa.repository.IntroRepository;
@Transactional
@Service("enterpriseIntroService")

public class EnterpriseIntroServiceImpl implements EnterpriseIntroService {
	
	@Autowired
	private IntroRepository introRepository;
	
	@Override
	public EnterpriseIntro save(EnterpriseIntro enterpriseIntro,
			Enterprise enterprise) {
		enterpriseIntro.setEnterprise(enterprise);
		return introRepository.save(enterpriseIntro);
	}

	@Override
	public EnterpriseIntro update(EnterpriseIntro enterpriseIntro) {
		// TODO Auto-generated method stub
		return introRepository.save(enterpriseIntro);
	}

	@Override
	public EnterpriseIntro findOneById(Long id) {
		// TODO Auto-generated method stub
		return introRepository.findOne(id);
	}

	@Override
	public void deleteById(Long id) {
	}

	@Override
	public List<EnterpriseIntro> getAllIntroById(Long id) {
		// TODO Auto-generated method stub
		return introRepository.getAllIntroById(id);
	}

	@Override
	public Page<EnterpriseIntro> findAllIntroByEnterprise(int pageNum,
			int pageSize, Enterprise enterprise) {
		Pageable dateDesc =  new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<EnterpriseIntro> onePage = introRepository.findIntroByEnterprise(enterprise, dateDesc);
		return onePage;
	}

}
