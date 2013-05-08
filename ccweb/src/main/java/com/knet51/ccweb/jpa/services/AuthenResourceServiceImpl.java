package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.AuthenResource;
import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.AuthenResourceRepository;

@Transactional
@Service("authenResourceService")
public class AuthenResourceServiceImpl implements AuthenResourceService {
	@Autowired
	private AuthenResourceRepository repository;

	@Override
	public List<AuthenResource> findAllByAuthen(Authentication authen) {
		return repository.findAllByauthentication(authen);
	}

	@Override
	public Page<AuthenResource> findAllByUser(int pageNumber, int pageSize,
			User user) {
		return null;
	}

	@Override
	public AuthenResource createAuthenResource(AuthenResource authenResource) {
		return repository.save(authenResource);
	}

	@Override
	public AuthenResource findOneById(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public void destoryById(Long id) {
		repository.delete(id);
	}

	@Override
	public AuthenResource updateAuthenResource(AuthenResource AuthenResource) {
		return repository.saveAndFlush(AuthenResource);
	}

	@Override
	public Page<AuthenResource> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
