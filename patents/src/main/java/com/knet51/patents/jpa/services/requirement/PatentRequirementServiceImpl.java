package com.knet51.patents.jpa.services.requirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;
import com.knet51.ccweb.jpa.repository.requirement.PatentRequirementRepository;
@Service("patentRequirementService")
public class PatentRequirementServiceImpl implements PatentRequirementService {
	
	@Autowired
	private PatentRequirementRepository repository;
	
	@Override
	public PatentRequirement create(PatentRequirement patentRequirement) {
		return repository.saveAndFlush(patentRequirement);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);

	}

	@Override
	public PatentRequirement update(PatentRequirement patentRequirement) {
		return repository.saveAndFlush(patentRequirement);
	}

	@Override
	public Page<PatentRequirement> findAllByUser(User user, int pageNumber,
			int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAllByUser(user, pageable);
	}

	@Override
	public List<PatentRequirement> findListByUser(User user) {
		Sort sort = new Sort(Direction.DESC,"id");
		return repository.findAllListByUser(user, sort);
	}

	@Override
	public Page<PatentRequirement> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAll(pageable);
	}

	@Override
	public List<PatentRequirement> findAllList() {
		return repository.findAll();
	}

	@Override
	public PatentRequirement findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Page<PatentRequirement> findAllByStatus(int pageNumber,
			int pageSize, Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAllByStatus(status, pageable);
	}

	@Override
	public List<PatentRequirement> findAllListByStatus(Integer status) {
		Sort sort = new Sort(Direction.DESC,"id");
		return repository.findAllListByStatus(status,sort);
	}

}
