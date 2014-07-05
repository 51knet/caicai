package com.graphene.web.jpa.repository.achievement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.entity.teacher.TeacherProject;
import com.graphene.web.jpa.entity.user.User;

public interface ProjectRepository extends JpaRepository<TeacherProject, Long>, JpaSpecificationExecutor<TeacherProject> {
	Page<TeacherProject> findProjectByUser(User user, Pageable pageable);
}
