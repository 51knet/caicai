package com.knet51.ccweb.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long>,
		JpaSpecificationExecutor<User> {
	@Query("select c from User c where c.email = :inputemail and c.password = :inputpsw")
	User getValidUser(@Param("inputemail") String email,
	                                 @Param("inputpsw") String psw);
}
