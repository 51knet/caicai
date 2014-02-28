package com.knet51.courses.jpa.services.projects;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.projects.Projects;

public interface ProjectsService {
	Page<Projects> findProjectsByStatus(int pageNumber, int pageSize,Integer status);
	List<Projects> findProjectsListByStatus(Integer status);
	
	Page<Projects> findProjectsByCompleteAndStatus(int pageNumber, int pageSize,Integer complete,Integer status);
	List<Projects> findProjectsListByCompleteAndStatus(Integer status,Integer complete);
	
	Projects findOne(Long id);
	
	Page<Projects> findProjectsByStatusAndProjectNameLike(Integer status, String projectName,int pageNumber, int pageSize);
}
