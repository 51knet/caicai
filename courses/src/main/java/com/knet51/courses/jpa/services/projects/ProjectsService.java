package com.knet51.courses.jpa.services.projects;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.projects.Projects;

public interface ProjectsService {
	Page<Projects> findProjectsByStatus(int pageNumber, int pageSize,Integer status);
	List<Projects> findProjectsListByStatus(Integer status);
	Projects findOne(Long id);
}
