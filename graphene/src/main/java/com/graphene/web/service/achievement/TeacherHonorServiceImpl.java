package com.graphene.web.service.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.dao.achievement.TeacherHonorDao;
import com.graphene.web.jpa.entity.teacher.TeacherHonor;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.achievement.HonorRepository;

@Transactional
@Service("teacherHonorService")

public class TeacherHonorServiceImpl implements TeacherHonorService {
	@Autowired
	private TeacherHonorDao honorDao; 
	
	@Autowired
	private HonorRepository honorRepository;
	
	@Override
	public TeacherHonor save(TeacherHonor teacherHonor,User user) {
		teacherHonor.setUser(user);
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
	public Page<TeacherHonor> findAllHonorByUser(int pageNum, int pageSize,
			User user) {
		Pageable dateDesc =  new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<TeacherHonor> onePage = honorRepository.findHonorByUser(user, dateDesc);
		return onePage;
	}

}
