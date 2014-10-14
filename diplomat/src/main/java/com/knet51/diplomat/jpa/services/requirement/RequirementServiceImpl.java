package com.knet51.diplomat.jpa.services.requirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.requirement.Requirement;
import com.knet51.ccweb.jpa.repository.requirement.RequirementRepository;

@Transactional
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
	public Requirement update(Requirement requirement) {
		return repository.saveAndFlush(requirement);
	}

	@Override
	public Page<Requirement> findRequireByUser(int pageNum, int pageSize,
			User user) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findRequireByUser(user, pageable);
	}

//	@Override
//	public Page<Requirement> findRequireByRequireType(int pageNum,
//			int pageSize, RequirType type) {
//		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
//		return repository.findReqireByRequirType(type, pageable);
//	}

	@Override
	public List<Requirement> findRequireListByUser(User user) {
		Sort sort = new Sort(Direction.DESC, "id");
		return repository.findRequirByUser(user, sort);
	}

//	@Override
//	public List<Requirement> findRequiteListByRequireType(RequirType type) {
//		Sort sort = new Sort(Direction.DESC, "id");
//		return repository.findRequirByRequirType(type,sort);
//	}

	@Override
	public Requirement findOne(Long require_id) {
		return repository.findOne(require_id);
	}

	@Override
	public Page<Requirement> findRequireAll(int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findAll(pageable);
	}

	@Override
	public Page<Requirement> findRequireByStatus(int pageNum, int pageSize,
			Integer status) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findRequireByStatus(status, pageable);
	}

	@Override
	public Page<Requirement> findRequireByTitleLike(int pageNum, int pageSize,
			String title) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findReqireByTitleLike(title, pageable);
	}

}
