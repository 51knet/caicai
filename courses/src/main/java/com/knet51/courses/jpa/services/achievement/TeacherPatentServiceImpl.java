package com.knet51.courses.jpa.services.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.teacherAchievement.TeacherPatentDao;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.repository.achievement.PatentRespository;
@Transactional
@Service("teacherPatentService")

public class TeacherPatentServiceImpl implements TeacherPatentService {
	@Autowired
	private TeacherPatentDao patentDao; 
	
	@Autowired
	private PatentRespository patentRespository;
	
	@Override
	public TeacherPatent save(TeacherPatent teacherPatent, Teacher teacher) {
		teacherPatent.setTeacher(teacher);
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
	public Page<TeacherPatent> findAllPatentByTeacher(int pageNum,
			int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<TeacherPatent> onePage = patentRespository.findThesisByTeacher(teacher, dateDesc);
		return onePage;
	}

}
