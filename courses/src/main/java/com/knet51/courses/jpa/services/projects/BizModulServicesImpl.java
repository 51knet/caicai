package com.knet51.courses.jpa.services.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.projects.BizModul;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.repository.projects.BizModulRepository;

@Service("bizModulServices")
public class BizModulServicesImpl implements BizModulService {

	@Autowired
	private BizModulRepository repository;
	
	@Override
	public BizModul findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public BizModul create(BizModul bizModul) {
		return repository.save(bizModul);
	}

	@Override
	public BizModul update(BizModul bizModul) {
		return repository.save(bizModul);
	}

	@Override
	public BizModul findByProjects(Projects projects) {
		BizModul bizModul = repository.findBizModulByProjectsId(projects.getId());
		return bizModul;
	}

	@Override
	public void dele(Long id) {
		repository.delete(id);
	}
	
}
