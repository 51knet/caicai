package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.teacherAchievement.TeacherProjectDao;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
@Transactional
@Service("teacherProjectService")

public class TeacherProjectServiceImpl implements TeacherProjectService {
	@Autowired
	private TeacherProjectDao projectDao; 
	
	@Override
	public TeacherProject save(TeacherProject teacherProject) {
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

}
