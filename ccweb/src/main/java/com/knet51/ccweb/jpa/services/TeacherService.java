package com.knet51.ccweb.jpa.services;

import com.knet51.ccweb.jpa.entities.Teacher;

public interface TeacherService {
	Teacher findOne(Long id);
	Teacher createTeacher(Teacher usr);
	Teacher updateTeacher(Teacher usr);
}
