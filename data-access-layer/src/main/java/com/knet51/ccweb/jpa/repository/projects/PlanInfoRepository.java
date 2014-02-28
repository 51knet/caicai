package com.knet51.ccweb.jpa.repository.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.entities.projects.PlanInfo;

public interface PlanInfoRepository extends JpaRepository<PlanInfo, Long>, JpaSpecificationExecutor<PlanInfo> {
	PlanInfo findPlanInfoByProjects(Projects project);
}
