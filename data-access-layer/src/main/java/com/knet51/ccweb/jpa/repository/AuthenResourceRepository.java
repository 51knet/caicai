package com.knet51.ccweb.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.AuthenResource;
import com.knet51.ccweb.jpa.entities.Authentication;

@Transactional
public interface AuthenResourceRepository  extends JpaRepository<AuthenResource, Long>, JpaSpecificationExecutor<AuthenResource>{
	
	List<AuthenResource> findAllByauthentication(Authentication authentication);
}
