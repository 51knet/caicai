package com.knet51.diplomat.jpa.services.applyright;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserRight;
import com.knet51.ccweb.jpa.repository.user.UserRightRepository;

@Service("userRightService")
public class UserRightServiceImpl implements UserRightService {
	@Autowired
	private UserRightRepository rightRepository; 
	@Override
	public UserRight findOne(Long id) {
		
		return rightRepository.findOne(id);
	}

	@Override
	public UserRight create(UserRight userRight) {
		
		return rightRepository.saveAndFlush(userRight);
	}

	@Override
	public UserRight update(UserRight userRight) {
		return rightRepository.saveAndFlush(userRight);
	}

	@Override
	public void delete(Long id) {
		rightRepository.delete(id);

	}

	@Override
	public List<UserRight> findUserRightListByUser(User user) {
		
		return rightRepository.findUserRightListByUser(user);
	}



}
