package com.knet51.ccweb.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.TeacherDao;
import com.knet51.ccweb.jpa.entities.Teacher;

@Transactional
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Teacher findOne(Long id) {
		return teacherDao.findById(id);
	}

	@Override
	public Teacher createTeacher(Teacher usr) {
		return teacherDao.save(usr);
	}

	@Override
	public Teacher updateTeacher(Teacher usr) {
		return teacherDao.update(usr);
	}
}
