package com.graphene.web.service.achievement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.dao.achievement.TeacherProjectDao;
import com.graphene.web.jpa.entity.teacher.TeacherProject;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.achievement.ProjectRepository;
@Transactional
@Service("teacherProjectService")

public class TeacherProjectServiceImpl implements TeacherProjectService {
	@Autowired
	private TeacherProjectDao projectDao; 
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public TeacherProject save(TeacherProject teacherProject, User user) {
		teacherProject.setUser(user);
		return projectDao.save(teacherProject);
	}

	@Override
	public TeacherProject update(TeacherProject teacherProject) {
		return projectDao.update(teacherProject);
	}

	@Override
	public TeacherProject findOneById(Long id) {
		return projectDao.findOneById(id);
	}

	@Override
	public void deleteById(Long id) {
		projectDao.deleteById(id);
	}

	@Override
	public List<TeacherProject> getAllProjectById(Long Id) {
		return projectDao.getAllProjectById(Id);
	}

	@Override
	public Page<TeacherProject> findAllProjectByUser(int pageNum,
			int pageSize, User user) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize,  Direction.DESC, "id");
		Page<TeacherProject> onePage = projectRepository.findProjectByUser(user, dateDesc);
		return onePage;
	}

}
