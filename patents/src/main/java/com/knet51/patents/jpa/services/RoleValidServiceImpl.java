package com.knet51.patents.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.RoleValid;
import com.knet51.ccweb.jpa.repository.RoleValidRepository;
@Service("roleValidService")
public class RoleValidServiceImpl implements RoleValidService {
	@Autowired
	private RoleValidRepository validRepository;
	@Override
	public RoleValid create(RoleValid investValid) {
		return validRepository.save(investValid);
	}

	@Override
	public RoleValid update(RoleValid investValid) {
		return validRepository.saveAndFlush(investValid);
	}

	@Override
	public void delete(Long id) {
		validRepository.delete(id);

	}

	@Override
	public RoleValid find(Long id) {
		return validRepository.findOne(id);
	}

	@Override
	public Page<RoleValid> findRoleValidPage(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return validRepository.findAll(pageable);
	}

	@Override
	public Page<RoleValid> findRoleValidByStatusAndApplypermit(Integer status,
			String applypermit, int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return validRepository.findRoleValidByStatusAndApplypermit(status, applypermit, pageable);
	}

}
