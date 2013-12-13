package com.knet51.ccweb.jpa.services.resume;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.WorkExp;
import com.knet51.ccweb.jpa.repository.WorkExpRepository;
@Transactional
@Service
public class WorkExpServiceImpl implements WorkExpService {
	
	@Autowired
	private WorkExpRepository workExpRepository;
	@Override
	public WorkExp createWorkExp(WorkExp workExp) {
		return workExpRepository.save(workExp);
	}

	@Override
	public WorkExp updateWorkExp(WorkExp workExp) {
		return workExpRepository.save(workExp);
	}

	@Override
	public void destory(Long id) {
		workExpRepository.delete(id);
	}

	@Override
	public List<WorkExp> findWorkList(Long teacher_id) {
		return workExpRepository.findWorkExpList(teacher_id);
	}

	@Override
	public WorkExp findOneById(Long id) {
		return workExpRepository.findOne(id);
	}

}
