package com.knet51.ccweb.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.StudentDao;
import com.knet51.ccweb.jpa.entities.Student;

@Transactional
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public Student findOne(Long id) {
		return studentDao.findById(id);
	}

	@Override
	public Student createStudent(Student student) {
		return studentDao.save(student);
	}

	@Override
	public Student updateStudent(Student usr) {
		return studentDao.update(usr);
	}
}
