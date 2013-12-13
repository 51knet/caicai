package com.knet51.ccweb.jpa.services.authentication;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.User;

public interface AuthenticationService {
	
	List<Authentication> findAllByUser(User user);
	Page<Authentication> findAllByUser(int pageNumber, int pageSize,
			User user);
	Authentication createAuthentication(Authentication authentication);
	Authentication findOneById(Long id);
	void destoryById(Long id);
	Authentication updateAuthentication(Authentication authentication);
	
	Page<Authentication> findAll(int pageNumber, int pageSize);
}
