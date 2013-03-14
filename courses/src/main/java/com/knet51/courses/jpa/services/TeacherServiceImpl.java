package com.knet51.courses.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.repository.TeacherRepository;

@Transactional
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public Teacher findOne(Long id) {
		return teacherRepository.findOne(id);
	}

	@Override
	public Teacher createTeacher(Teacher usr) {
		return teacherRepository.save(usr);
	}

	@Override
	public Teacher updateTeacher(Teacher usr) {
		return teacherRepository.save(usr);
	}

	@Override
	public List<Teacher> findAllTeacher() {
		return teacherRepository.findAll();
	}
	@Override
	public List<Teacher> findTeacherByIsEnterprise() {
		List<Teacher> list=teacherRepository.findByIsEnterprise();
		return list;
	}

	@Override
	public Page<Teacher> findAll(int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<Teacher> onePage = teacherRepository.findAll(pageable);
		return onePage;
	}

	@Override
	public List<Teacher> findEnterpriseByisEnterprise(String isEnterprise) {
		List<Teacher> list=teacherRepository.findEnterpriseByisEnterprise(isEnterprise);
		System.out.println("======================="+list.size());
		return list;
	}

	@Override
	public Page<Teacher> findAllEnterpriseByisEnterprise(int pageNum, int pageSize,
			String isEnterprise) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC,"id");
		Page<Teacher> onePage = teacherRepository.findEnterpriseByIsEnterprise(isEnterprise, pageable);
		return onePage;
	}

	@Override
	public Page<Teacher> findAllTeacher(int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC,"id");
		Page<Teacher> onePage = teacherRepository.findTeacherByIsEnterpriseIsNull(pageable);
		return onePage;
	}

}
