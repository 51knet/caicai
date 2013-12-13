package com.knet51.ccweb.jpa.services.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.AuthenticationRepository;

@Transactional
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public List<Authentication> findAllByUser(User user) {
		Sort sort = new Sort(Direction.DESC, "id");
		List<Authentication> authenticationList = authenticationRepository.findAllByUser(user, sort);
		return authenticationList;
	}

	@Override
	public Authentication createAuthentication(Authentication authentication) {
		return authenticationRepository.saveAndFlush(authentication);
	}

	@Override
	public Authentication findOneById(Long id) {
		return authenticationRepository.findOne(id);
	}

	@Override
	public void destoryById(Long id) {
		authenticationRepository.delete(id);
	}

	@Override
	public Authentication updateAuthentication(Authentication authentication) {
		return authenticationRepository.saveAndFlush(authentication);
	}

	@Override
	public Page<Authentication> findAllByUser(int pageNumber, int pageSize,
			User user) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Authentication> onePage = authenticationRepository.findAllByUser(user, pageable);
		return onePage;
	}

	@Override
	public Page<Authentication> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Authentication> onePage = authenticationRepository.findAll(pageable);
		return onePage;
	}

}
