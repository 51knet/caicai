package com.knet51.courses.jpa.services.projects;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.UserRight;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.repository.OrderRepository;
import com.knet51.ccweb.jpa.repository.projects.ProjectsRepository;
import com.knet51.ccweb.jpa.repository.user.UserRightRepository;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;

@Service("projectsService")
public class ProjectsServiceImpl implements ProjectsService {
	@Autowired
	private ProjectsRepository repository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRightRepository rightRepository;

	@Override
	public Page<Projects> findProjectsByStatus(int pageNumber, int pageSize,
			Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findProjectsByStatus(status, pageable);
	}

	@Override
	public List<Projects> findProjectsListByStatus(Integer status) {
		
		return repository.findProjectsListByStatus(status);
	}

	@Override
	public Projects findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Page<Projects> findProjectsByCompleteAndStatus(int pageNumber,
			int pageSize, Integer complete, Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findProjectsByCompleteAndStatus(complete, status, pageable);
	}

	@Override
	public List<Projects> findProjectsListByCompleteAndStatus(Integer status,
			Integer complete) {
		return repository.findProjectsListByCompleteAndStatus(complete, status);
	}

	@Override
	public Page<Projects> findProjectsByStatusAndProjectNameLike(
			Integer status, String projectName,int pageNumber,int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id","date");
		return repository.findProjectsByStatusAndProjectNameLike(status, "%"+projectName+"%", pageable);
	}

	@Override
	public Projects create(Projects projects) {
		return repository.save(projects);
	}
	
	@Override
	public boolean hasLedInvestorInProjects(Long project_id, HttpSession session) {
		boolean flag = false;
		List<UserOrder> order = orderRepository.findOrderByStatusAndProjectsId("完成",project_id);
		for (UserOrder userOrder : order) {
			User user = userOrder.getUser();
			List<UserRight> userRights = rightRepository.findUserRightListByUser(user);
			for (UserRight userRight : userRights) {
				if(userRight.getUserRight().equals("ledinvestor")){
					flag = true;
				}
			}
		}
		return flag;
	}

}
