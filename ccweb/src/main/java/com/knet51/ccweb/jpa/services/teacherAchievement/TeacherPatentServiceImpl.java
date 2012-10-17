package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.teacherAchievement.TeacherPatentDao;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
@Transactional
@Service("teacherPatentService")

public class TeacherPatentServiceImpl implements TeacherPatentService {
	@Autowired
	private TeacherPatentDao patentDao; 
	
	@Override
	public TeacherPatent save(TeacherPatent teacherPatent, Teacher teacher) {
		teacherPatent.setTeacher(teacher);
		return patentDao.save(teacherPatent);
	}

	@Override
	public TeacherPatent update(TeacherPatent teacherPatent) {
		return patentDao.update(teacherPatent);
	}

	@Override
	public TeacherPatent findOneById(Long id) {
		return patentDao.findOneById(id);
	}

	@Override
	public void deleteById(Long id) {
		patentDao.deleteById(id);
	}

	@Override
	public List<TeacherPatent> getAllPatentById(Long Id) {
		return patentDao.getAllPatentById(Id);
	}

}
