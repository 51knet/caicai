package com.knet51.patents.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.repository.TeacherRepository;

@Transactional
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher findOne(Long id) {
		return teacherRepository.findOne(id);
	}

	@Override
	public Teacher createTeacher(Teacher usr) {
		return teacherRepository.save(usr);
	}

	@Override
	public Teacher updateTeacher(Teacher usr) {
		return teacherRepository.save(usr);
	}

}
