package com.graphene.web.jpa.repository.achievement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.teacher.TeacherHonor;
import com.graphene.web.jpa.entity.user.User;

public interface HonorRepository extends JpaRepository<TeacherHonor, Long>, JpaSpecificationExecutor<TeacherHonor> {
	Page<TeacherHonor> findHonorByUser(User user, Pageable pageable);
}
