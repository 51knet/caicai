package com.knet51.courses.jpa.services.projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.repository.projects.ProjectsRepository;
@Transactional
@Service("projectsService")
public class ProjectsServiceImpl implements ProjectsService {
	@Autowired
	private ProjectsRepository repository;

	@Override
	public Page<Projects> findProjectsByStatus(int pageNumber, int pageSize,
			Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findProjectsByStatus(status, pageable);
	}

	@Override
	public List<Projects> findProjectsListByStatus(Integer status) {
		
		return repository.findProjectsListByStatus(status);
	}

	@Override
	public Projects findOne(Long id) {
		return repository.findOne(id);
	}

}
