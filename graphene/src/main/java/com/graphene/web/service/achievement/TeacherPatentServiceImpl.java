package com.graphene.web.service.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.dao.achievement.TeacherPatentDao;
import com.graphene.web.jpa.entity.teacher.Teacher;
import com.graphene.web.jpa.entity.teacher.TeacherPatent;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.achievement.PatentRespository;


@Transactional
@Service("teacherPatentService")

public class TeacherPatentServiceImpl implements TeacherPatentService {
	@Autowired
	private TeacherPatentDao patentDao; 
	
	@Autowired
	private PatentRespository patentRespository;
	
	@Override
	public TeacherPatent save(TeacherPatent teacherPatent, User user) {
		teacherPatent.setUser(user);
		return patentDao.save(teacherPatent);
	}

	@Override
	public TeacherPatent update(TeacherPatent teacherPatent) {
		return patentDao.update(teacherPatent);
	}

	@Override
	public TeacherPatent findOneById(Long id) {
		return patentDao.findOneById(id);
	}

	@Override
	public void deleteById(Long id) {
		patentDao.deleteById(id);
	}

	@Override
	public List<TeacherPatent> getAllPatentById(Long Id) {
		return patentDao.getAllPatentById(Id);
	}

	@Override
	public Page<TeacherPatent> findAllPatentByUser(int pageNum,
			int pageSize, User user) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<TeacherPatent> onePage = patentRespository.findThesisByUser(user, dateDesc);
		return onePage;
	}

}
