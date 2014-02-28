package com.knet51.courses.jpa.services.projects;

import com.knet51.ccweb.jpa.entities.projects.TeamInfo;
import com.knet51.ccweb.jpa.entities.projects.Projects;

public interface TeamInfoService {
	TeamInfo findOne(Long id);
	TeamInfo create(TeamInfo teamInfo);
	TeamInfo update(TeamInfo teamInfo);
	TeamInfo findByProjects(Projects projects);
	void dele(Long id);
}
