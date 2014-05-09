package com.knet51.patents.jpa.services.incubator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.incubator.IncubatInfor;
import com.knet51.ccweb.jpa.repository.incubator.IncubatInforRepository;
import com.knet51.patents.jpa.services.incubator.IncubatInforService;
@Service("incubatorInforService")
public class IncubatInforServiceImpl implements IncubatInforService {
	
	@Autowired
	private IncubatInforRepository repository;
	
	@Override
	public IncubatInfor findOne(Long id) {
		
		return repository.findOne(id);
	}

	@Override
	public IncubatInfor create(IncubatInfor incubatInfor) {
		
		return repository.saveAndFlush(incubatInfor);
	}

	@Override
	public IncubatInfor update(IncubatInfor incubatInfor) {
		return repository.saveAndFlush(incubatInfor);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);

	}

}
