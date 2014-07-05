package com.graphene.web.service;

import com.graphene.web.jpa.entity.teacher.Teacher;


public interface TeacherService {
	Teacher findOne(Long id);
//	Teacher findByUserId(Long userId);
	Teacher createTeacher(Teacher usr);
	Teacher updateTeacher(Teacher usr);
//	Teacher saveInfo(Teacher usr);
}
