package com.knet51.ccweb.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.repository.StudentRepository;

public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student findOne(Long id) {
		return studentRepository.findOne(id);
	}
	@Override
	public Student createStudent(Student stu) {
		return studentRepository.save(stu);
	}
	@Override
	public Student updateStudent(Student stu) {
		return studentRepository.save(stu);
	}

}
