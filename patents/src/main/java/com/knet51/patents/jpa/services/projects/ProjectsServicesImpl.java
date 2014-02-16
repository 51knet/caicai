package com.knet51.patents.jpa.services.projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.repository.projects.ProjectsRepository;

@Service("projectsServices")
public class ProjectsServicesImpl implements ProjectsService {
	
	@Autowired
	private ProjectsRepository repository;
	
	@Override
	public Projects findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Projects create(Projects projects) {
		return repository.saveAndFlush(projects);
	}

	@Override
	public Projects update(Projects projects) {
		return repository.saveAndFlush(projects);
	}

	@Override
	public void dele(Long id) {
		repository.delete(id);
	}

	@Override
	public Page<Projects> findProjectsByStatus(int pageNumber, int pageSize,
			Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findProjectsByStatus(status, pageable);
	}

	@Override
	public List<Projects> findProjectsListByStatus(Integer status) {
		return repository.findProjectsListByStatus(status);
	}

	@Override
	public Page<Projects> findProjectsByUser(User user, int pageNumber,
			int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findProjectsByUser(user, pageable);
	}

	@Override
	public List<Projects> findProjectsListByUser(User user) {
		
		return repository.findProjectsListByUser(user);
	}

}
