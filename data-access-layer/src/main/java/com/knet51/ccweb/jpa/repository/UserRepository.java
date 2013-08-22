package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	@Query("select c from User c where c.email = :inputemail")
	User getValidEmail(@Param("inputemail") String email);
	
	Page<User> findUserByRole(String Role, Pageable pageable);
	
	List<User> findUserByRole(String Role);
	
	List<User> findUserByNameLike(String name);
}
