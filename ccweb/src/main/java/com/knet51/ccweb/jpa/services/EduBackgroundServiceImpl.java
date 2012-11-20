package com.knet51.ccweb.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.repository.EduBackgroundRepository;
@Transactional
@Service("eduBackgroundService")
public class EduBackgroundServiceImpl implements EduBackgroundService {
	
	@Autowired
	private EduBackgroundRepository eduBackgroundRepository;

	@Override
	public EduBackground createEduBackground(EduBackground eduBackground) {
		return eduBackgroundRepository.save(eduBackground);
	}

	@Override
	public EduBackground findEduInfoByteacherId(Long teacher_id) {
		return eduBackgroundRepository.findOneByTeacherId(teacher_id);
	}

	@Override
	public EduBackground updateEduBackground(EduBackground eduBackground) {
		return eduBackgroundRepository.save(eduBackground);
	}

}
