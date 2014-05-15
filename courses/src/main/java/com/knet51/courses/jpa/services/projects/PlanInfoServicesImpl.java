package com.knet51.courses.jpa.services.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.projects.PlanInfo;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.repository.projects.PlanInfoRepository;

@Service("planInfoServices")
public class PlanInfoServicesImpl implements PlanInfoService {

	@Autowired
	private PlanInfoRepository repository;
	
	@Override
	public PlanInfo findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public PlanInfo create(PlanInfo planInfo) {
		return repository.save(planInfo);
	}

	@Override
	public PlanInfo update(PlanInfo planInfo) {
		return repository.save(planInfo);
	}

	@Override
	public PlanInfo findByProjects(Projects project) {
		PlanInfo planInfo = repository.findPlanInfoByProject(project);

		return planInfo;
	}

	@Override
	public void dele(Long id) {
		repository.delete(id);
	}
	
}
