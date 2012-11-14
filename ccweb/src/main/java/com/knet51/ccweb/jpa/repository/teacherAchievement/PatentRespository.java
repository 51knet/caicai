package com.knet51.ccweb.jpa.repository.teacherAchievement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
@Transactional
public interface PatentRespository extends JpaRepository<TeacherPatent, Long>, JpaSpecificationExecutor<TeacherPatent> {
	Page<TeacherPatent> findThesisByTeacher(Teacher teacher, Pageable pageable);
}
