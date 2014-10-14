package com.knet51.diplomat.jpa.services.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.projects.TeamInfo;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.repository.projects.TeamInfoRepository;
@Transactional
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
		if(teamInfo == null){
			teamInfo = new TeamInfo(projects);
			repository.save(teamInfo);
		}
		return teamInfo;
	}

	@Override
	public void dele(Long id) {
		repository.delete(id);
	}
	
}
