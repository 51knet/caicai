package com.knet51.courses.jpa.services.projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.knet51.ccweb.jpa.entities.projects.Rzfh;
import com.knet51.ccweb.jpa.repository.projects.RzfhRepository;

@Service("rzfhService")
public class RzfhServiceImpl implements RzfhService {
	
	@Autowired
	private RzfhRepository repository;
	
	
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
	public Page<Rzfh> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findAll(pageable);
	}

	

}
