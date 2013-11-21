package com.knet51.courses.jpa.services.requirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.RequirType;
import com.knet51.ccweb.jpa.entities.Requirement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.RequirementRepository;
@Service("requirementService")
public class RequirementServiceImpl implements RequirementService {
	@Autowired
	private RequirementRepository repository;
	
	@Override
	public Requirement create(Requirement requirement) {
		return repository.save(requirement);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public void update(Requirement requirement) {
		repository.saveAndFlush(requirement);
	}

	@Override
	public Page<Requirement> findRequireByUser(int pageNum, int pageSize,
			User user) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findRequireByUser(user, pageable);
	}

	@Override
	public Page<Requirement> findRequireByRequireType(int pageNum,
			int pageSize, RequirType type) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findReqireByRequirType(type, pageable);
	}

	@Override
	public List<Requirement> findRequireListByUser(User user) {
		return repository.findRequirByUser(user);
	}

	@Override
	public List<Requirement> findRequiteListByRequireType(RequirType type) {
		return repository.findRequirByRequirType(type);
	}

	@Override
	public Requirement findOne(Long require_id) {
		return repository.findOne(require_id);
	}

	@Override
	public List<Requirement> findAll() {
		return repository.findAll();
	}

}
