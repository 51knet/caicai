package com.knet51.diplomat.jpa.services.projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.projects.Rzfh;
import com.knet51.ccweb.jpa.repository.projects.RzfhRepository;

@Service("rzfhService")
public class RzfhServiceImpl implements RzfhService {
	
	@Autowired
	private RzfhRepository repository;
	
	@Override
	public Rzfh findOne(Long id) {
		return repository.findOne(id);
	}
	@Transactional
	@Override
	public Rzfh create(Rzfh rzfh) {
		return repository.save(rzfh);
	}
	@Transactional
	@Override
	public Rzfh update(Rzfh rzfh) {
		return repository.saveAndFlush(rzfh);
	}
	@Transactional
	@Override
	public void dele(Long id) {
		repository.delete(id);
	}
	@Override
	public Page<Rzfh> findRzfhByStatusAndTypes(int pageNumber, int pageSize,
			Integer status, String types) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findRzfhByStatusAndTypes(status, types, pageable);
	}
	@Override
	public List<Rzfh> findRzfhListByStatusAndTypes(Integer status,String types) {
		return repository.findRzfhListByStatusAndTypes(status, types);
	}
	@Override
	public Page<Rzfh> findRzfhByUserAndTypes(User user, String types,
			int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findRzfhByUserAndTypes(user, types, pageable);
	}
	@Override
	public List<Rzfh> findRzfhListByUserAndTypes(User user, String types) {
		return repository.findRzfhListByUserAndTypes(user, types);
	}
	@Override
	public Page<Rzfh> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findAll(pageable);
	}

	

}
