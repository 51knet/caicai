package com.knet51.patents.jpa.services.achievement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.dao.teacherAchievement.TeacherProjectDao;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.repository.achievement.ProjectRepository;
@Transactional
@Service("teacherProjectService")

public class TeacherProjectServiceImpl implements TeacherProjectService {
	@Autowired
	private TeacherProjectDao projectDao; 
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public TeacherProject save(TeacherProject teacherProject, Teacher teacher) {
		teacherProject.setTeacher(teacher);
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
	public Page<TeacherProject> findAllProjectByTeacher(int pageNum,
			int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize,  Direction.DESC, "id");
		Page<TeacherProject> onePage = projectRepository.findProjectByTeacher(teacher, dateDesc);
		return onePage;
	}

}
