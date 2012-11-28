package com.knet51.ccweb.jpa.services;

import com.knet51.ccweb.jpa.entities.Student;
public interface StudentService {
	Student findOne(Long id);
	Student createStudent(Student stu);
	Student updateStudent(Student stu);
}
