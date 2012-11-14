package com.knet51.ccweb.jpa.repository.teacherAchievement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;

public interface ThesisRepository extends JpaRepository<TeacherThesis, Long>, JpaSpecificationExecutor<TeacherThesis> {
	Page<TeacherThesis> findThesisByTeacher(Teacher teacher, Pageable pageable);
}
