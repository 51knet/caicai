package com.knet51.ccweb.jpa.repository.teacherAchievement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
@Transactional
public interface HonorRepository extends JpaRepository<TeacherHonor, Long>, JpaSpecificationExecutor<TeacherHonor> {
	Page<TeacherHonor> findHonorByTeacher(Teacher teacher, Pageable pageable);
}
