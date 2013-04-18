package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.User;

public interface AuthenticationRepository extends JpaRepository<Authentication,Long>,JpaSpecificationExecutor<Authentication> {
	List<Authentication> findAllByUser(User user, Sort sort);
	Page<Authentication> findAllByUser(User user , Pageable pageable);
}
