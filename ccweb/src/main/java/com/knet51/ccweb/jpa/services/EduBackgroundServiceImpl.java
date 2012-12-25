package com.knet51.ccweb.jpa.services;

import java.util.List;

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
	public EduBackground updateEduBackground(EduBackground eduBackground) {
		return eduBackgroundRepository.save(eduBackground);
	}

	@Override
	public List<EduBackground> findEduListByTeacherId(Long teacher_id) {
		return eduBackgroundRepository.findEduBackgroundByTeacherid(teacher_id);
	}

	@Override
	public void destory(Long id) {
		eduBackgroundRepository.delete(id);
	}



	@Override
	public EduBackground findOneById(Long Id) {
		EduBackground eduBackground = eduBackgroundRepository.findOne(Id);
		return eduBackground;
	}

}
