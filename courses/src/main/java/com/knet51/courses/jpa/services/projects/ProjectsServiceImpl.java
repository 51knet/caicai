package com.knet51.courses.jpa.services.projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.repository.projects.ProjectsRepository;

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

	@Override
	public Page<Projects> findProjectsByCompleteAndStatus(int pageNumber,
			int pageSize, Integer complete, Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findProjectsByCompleteAndStatus(complete, status, pageable);
	}

	@Override
	public List<Projects> findProjectsListByCompleteAndStatus(Integer status,
			Integer complete) {
		return repository.findProjectsListByCompleteAndStatus(complete, status);
	}

	@Override
	public Page<Projects> findProjectsByStatusAndProjectNameLike(
			Integer status, String projectName,int pageNumber,int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findProjectsByStatusAndProjectNameLike(status, "%"+projectName+"%", pageable);
	}

	@Override
	public Projects create(Projects projects) {
		return repository.save(projects);
	}

}
