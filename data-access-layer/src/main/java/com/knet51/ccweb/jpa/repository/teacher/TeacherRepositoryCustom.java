package com.knet51.ccweb.jpa.repository.teacher;

import java.util.List;

import com.knet51.ccweb.jpa.entities.Teacher;

public interface TeacherRepositoryCustom {
	List<Teacher> findByIsEnterprise();
}
