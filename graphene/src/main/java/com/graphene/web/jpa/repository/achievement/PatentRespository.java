package com.graphene.web.jpa.repository.achievement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.entity.teacher.Teacher;
import com.graphene.web.jpa.entity.teacher.TeacherPatent;
import com.graphene.web.jpa.entity.user.User;

public interface PatentRespository extends JpaRepository<TeacherPatent, Long>, JpaSpecificationExecutor<TeacherPatent> {
	Page<TeacherPatent> findThesisByUser(User user, Pageable pageable);
}
