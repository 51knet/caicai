package com.knet51.diplomat.jpa.services.technology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
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
	public Page<Technology> findAllByUser(User user, int pageNumber,
			int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAllByUser(user, pageable);
	}

	@Override
	public List<Technology> findListByUser(User user) {
		
		return repository.findAllListByUser(user);
	}

	@Override
	public Page<Technology> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAll(pageable);
	}

	@Override
	public List<Technology> findAllList() {
		return repository.findAll();
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
	public Page<Technology> findAllByFocus(int pageNumber, int pageSize,
			Integer focus) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAllByFocus(focus, pageable);
	}

	@Override
	public Page<Technology> findAllByUserAndStatus(User user, int status,
			int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findAllByUserAndStatus(user, status, pageable);
	}

}
