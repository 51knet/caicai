package com.knet51.courses.jpa.services.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.projects.TeamInfo;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.repository.projects.TeamInfoRepository;

@Service("teamInfoServices")
public class TeamInfoServicesImpl implements TeamInfoService {

	@Autowired
	private TeamInfoRepository repository;
	
	@Override
	public TeamInfo findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public TeamInfo create(TeamInfo teamInfo) {
		return repository.save(teamInfo);
	}

	@Override
	public TeamInfo update(TeamInfo teamInfo) {
		return repository.save(teamInfo);
	}

	@Override
	public TeamInfo findByProjects(Projects projects) {
		TeamInfo teamInfo = repository.findTeamInfoByProjectsId(projects.getId());

		return teamInfo;
	}

	@Override
	public void dele(Long id) {
		repository.delete(id);
	}
	
}
