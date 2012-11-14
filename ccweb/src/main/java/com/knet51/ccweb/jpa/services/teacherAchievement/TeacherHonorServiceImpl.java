package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.teacherAchievement.TeacherHonorDao;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.repository.teacherAchievement.HonorRepository;
@Transactional
@Service("teacherHonorService")

public class TeacherHonorServiceImpl implements TeacherHonorService {
	@Autowired
	private TeacherHonorDao honorDao; 
	
	@Autowired
	private HonorRepository honorRepository;
	
	@Override
	public TeacherHonor save(TeacherHonor teacherHonor,Teacher teacher) {
		teacherHonor.setTeacher(teacher);
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

	@Override
	public Page<TeacherHonor> findAllResouById(int pageNum, int pageSize,
			Teacher teacher) {
		Pageable dateDesc =  new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<TeacherHonor> onePage = honorRepository.findHonorByTeacher(teacher, dateDesc);
		return onePage;
	}

}
