package com.knet51.ccweb.jpa.repository.teacherAchievement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
@Transactional
public interface ProjectRepository extends JpaRepository<TeacherProject, Long>, JpaSpecificationExecutor<TeacherProject> {
	Page<TeacherProject> findProjectByTeacher(Teacher teacher, Pageable pageable);
}
