package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.Activity;
import com.knet51.ccweb.jpa.entities.User;

public interface ActivityRepository extends JpaRepository<Activity, Long>,JpaSpecificationExecutor<Activity> {
	Page<Activity> findActivityByUser(User user ,Pageable pageable);
}
