package com.graphene.web.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.Activity;
import com.graphene.web.jpa.entity.user.User;


public interface ActivityRepository extends JpaRepository<Activity, Long>,JpaSpecificationExecutor<Activity> {
	Page<Activity> findActivityByUser(User user ,Pageable pageable);
}
