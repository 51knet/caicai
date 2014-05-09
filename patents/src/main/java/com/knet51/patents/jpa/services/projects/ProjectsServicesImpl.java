package com.knet51.patents.jpa.services.projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.projects.BizModul;
import com.knet51.ccweb.jpa.entities.projects.PlanInfo;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.entities.projects.TeamInfo;
import com.knet51.ccweb.jpa.repository.projects.BizModulRepository;
import com.knet51.ccweb.jpa.repository.projects.PlanInfoRepository;
import com.knet51.ccweb.jpa.repository.projects.ProjectsRepository;
import com.knet51.ccweb.jpa.repository.projects.TeamInfoRepository;

@Service("projectsServices")
public class ProjectsServicesImpl implements ProjectsService {
	
	@Autowired
	private ProjectsRepository repository;	
	@Autowired
	private PlanInfoRepository planRepository;	
	@Autowired
	private BizModulRepository bizRepository;
	@Autowired
	private TeamInfoRepository teamRepository;
	
	@Override
	public Projects findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Projects create(Projects projects) {
		return repository.saveAndFlush(projects);
	}

	@Override
	public Projects update(Projects projects) {
		return repository.saveAndFlush(projects);
	}
	
	@Transactional
	@Override
	public void dele(Long id) {
		Projects projects = repository.findOne(id);
		PlanInfo planInfo = planRepository.findPlanInfoByProjects(projects);
		BizModul bizModul = bizRepository.findBizModulByProjects(projects);
		TeamInfo teamInfo = teamRepository.findTeamInfoByProjects(projects);
		try {
			if(planInfo != null && bizModul !=null &&  teamInfo != null ){
				planRepository.delete(planInfo);
				bizRepository.delete(bizModul);
				teamRepository.delete(teamInfo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		repository.delete(id);
	}

	@Override
	public Page<Projects> findProjectsByStatus(int pageNumber, int pageSize,
			Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findProjectsByStatus(status, pageable);
	}

	@Override
	public List<Projects> findProjectsListByStatus(Integer status) {
		return repository.findProjectsListByStatus(status);
	}

	@Override
	public Page<Projects> findProjectsByUser(User user, int pageNumber,
			int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findProjectsByUser(user, pageable);
	}

	@Override
	public List<Projects> findProjectsListByUser(User user) {
		
		return repository.findProjectsListByUser(user);
	}

	@Override
	public Page<Projects> findProjectsPage(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findAll(pageable);
	}

	@Override
	public Page<Projects> findOrderProjectsByUser(Long userid, int pageNumber,
			int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return repository.findOrderProjectsByUser(userid, pageable);
	}

}
