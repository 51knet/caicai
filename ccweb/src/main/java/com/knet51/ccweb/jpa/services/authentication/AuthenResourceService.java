package com.knet51.ccweb.jpa.services.authentication;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.AuthenResource;
import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.User;

public interface AuthenResourceService {
	
	List<AuthenResource> findAllByAuthen(Authentication authen);
	Page<AuthenResource> findAllByUser(int pageNumber, int pageSize,
			User user);
	AuthenResource createAuthenResource(AuthenResource authenResource);
	AuthenResource findOneById(Long id);
	void destoryById(Long id);
	AuthenResource updateAuthenResource(AuthenResource authenResource);
	
	Page<AuthenResource> findAll(int pageNumber, int pageSize);
}
