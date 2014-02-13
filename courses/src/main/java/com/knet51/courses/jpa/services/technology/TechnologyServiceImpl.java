package com.knet51.courses.jpa.services.technology;

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
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.ccweb.jpa.repository.technology.TechnologyRepository;
@Service("technologyService")
public class TechnologyServiceImpl implements TechnologyService {
	@Autowired
	private TechnologyRepository repository;
	@Override
	public Technology create(Technology technology) {
		return repository.saveAndFlush(technology);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Technology update(Technology technology) {
		return repository.saveAndFlush(technology);
	}

	@Override
	public Technology findOne(Long id) {
		return repository.findOne(id);
	}


	@Override
	public Page<Technology> findAllByStatus(int pageNumber,
			int pageSize, Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAllByStatus(status, pageable);
	}

	@Override
	public List<Technology> findAllListByStatus(Integer status) {
		
		return repository.findAllListByStatus(status);
	}


	@Override
	public Page<Technology> findAllByFocusAndStatus(int pageNumber,
			int pageSize, Integer focus, Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAllByFocusAndStatus(focus, status, pageable);
		
	}

	@Override
	public List<Technology> findListByFocusAndStatus(Integer focus,
			Integer status) {
		Sort sort = new Sort(Direction.DESC, "id","date");
		return repository.findAllListByFocusAndStatus(focus, status, sort);
	}

}
