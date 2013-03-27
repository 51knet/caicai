package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.EnterpriseTeacherRepository;
@Transactional
@Service("enterpriseTeacherService")
public class EnterpriseTeacherServiceImpl implements EnterpriseTeacherService {
	
	@Autowired
	private EnterpriseTeacherRepository repository;

	@Override
	public List<EnterpriseTeacher> findTeacherByEnterprise(User enterprise) {
		return repository.findTeacherByUser(enterprise);
	}

	@Override
	public Page<EnterpriseTeacher> findTeacherByEnterprise(int pageNum,
			int pageSize, User enterprise) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<EnterpriseTeacher> onePage = repository.findTeacherByUser(enterprise, dateDesc);
		return onePage;
	}

	@Override
	public EnterpriseTeacher createTeacher(EnterpriseTeacher eTeacher) {
		return repository.save(eTeacher);
	}

	@Override
	public EnterpriseTeacher updateTeacher(EnterpriseTeacher eTeacher) {
		return repository.saveAndFlush(eTeacher);
	}

	@Override
	public void destoryTeacher(Long eTeacherid) {
		repository.delete(eTeacherid);
	}

	@Override
	public EnterpriseTeacher findOneById(Long id) {
		
		return repository.findOne(id);
	}

}
