package com.knet51.ccweb.jpa.repository.projects;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.projects.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Long>, JpaSpecificationExecutor<Projects> {
	Page<Projects> findProjectsByStatus(Integer status, Pageable pageable);
	List<Projects> findProjectsListByStatus(Integer status);
	
	Page<Projects> findProjectsByCompleteAndStatus(Integer complete,Integer status, Pageable pageable);
	List<Projects> findProjectsListByCompleteAndStatus(Integer complete,Integer status);
	
	Page<Projects> findProjectsByUser(User user, Pageable pageable);
	List<Projects> findProjectsListByUser(User user);
	
	Page<Projects> findProjectsByStatusAndProjectNameLike(Integer status,String projectName, Pageable pageable);
	List<Projects> findProjectsListByProjectNameLike(String projectName);
}
