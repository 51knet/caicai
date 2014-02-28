package com.knet51.ccweb.jpa.repository.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.projects.BizModul;
import com.knet51.ccweb.jpa.entities.projects.Projects;

public interface BizModulRepository extends JpaRepository<BizModul, Long>, JpaSpecificationExecutor<BizModul> {
	BizModul findBizModulByProjects(Projects project);
}
