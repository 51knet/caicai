package com.knet51.courses.jpa.services.projects;

import com.knet51.ccweb.jpa.entities.projects.PlanInfo;
import com.knet51.ccweb.jpa.entities.projects.Projects;

public interface PlanInfoService {
	PlanInfo findOne(Long id);
	PlanInfo create(PlanInfo planInfo);
	PlanInfo update(PlanInfo planInfo);
	PlanInfo findByProjects(Projects projects);
	void dele(Long id);
}
