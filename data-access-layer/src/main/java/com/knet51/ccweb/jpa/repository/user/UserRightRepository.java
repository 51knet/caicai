package com.knet51.ccweb.jpa.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserRight;

public interface UserRightRepository extends JpaRepository<UserRight, Long>,JpaSpecificationExecutor<UserRight> {
	List<UserRight> findUserRightListByUser(User user);
}
