package com.knet51.diplomat.jpa.services.projects;

import com.knet51.ccweb.jpa.entities.projects.BizModul;
import com.knet51.ccweb.jpa.entities.projects.Projects;

public interface BizModulService {
	BizModul findOne(Long id);
	BizModul create(BizModul bizModul);
	BizModul update(BizModul bizModul);
	BizModul findByProjects(Projects projects);
	void dele(Long id);
}
