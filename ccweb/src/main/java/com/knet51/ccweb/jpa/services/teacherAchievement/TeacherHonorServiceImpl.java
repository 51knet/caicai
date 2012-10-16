package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.teacherAchievement.TeacherHonorDao;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
@Transactional
@Service("teacherHonorService")

public class TeacherHonorServiceImpl implements TeacherHonorService {
	@Autowired
	private TeacherHonorDao honorDao; 
	
	@Override
	public TeacherHonor save(TeacherHonor teacherHonor) {
		return honorDao.save(teacherHonor);
	}

	@Override
	public TeacherHonor update(TeacherHonor teacherHonor) {
		return honorDao.update(teacherHonor);
	}

	@Override
	public TeacherHonor findOneById(Long id) {
		return honorDao.findOneById(id);
	}

	@Override
	public void deleteById(Long id) {
		honorDao.deleteById(id);
	}

	@Override
	public List<TeacherHonor> getAllHonorById(Long Id) {
		return honorDao.getAllHonorById(Id);
	}

}
