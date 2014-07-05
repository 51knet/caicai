package com.graphene.web.service.achievement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.dao.achievement.TeacherThesisDao;
import com.graphene.web.jpa.entity.teacher.TeacherThesis;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.achievement.ThesisRepository;

@Transactional
@Service("teacherThesisService")

public class TeacherThesisServiceImpl implements TeacherThesisService {
	@Autowired
	private TeacherThesisDao thesisDao; 
	
	@Autowired
	private ThesisRepository thesisRepository;
	
	@Override
	public TeacherThesis save(TeacherThesis teacherThesis,User user) {
		teacherThesis.setUser(user);
		return thesisDao.save(teacherThesis);
	}

	@Override
	public TeacherThesis update(TeacherThesis teacherThesis) {
		return thesisDao.update(teacherThesis);
	}

	@Override
	public TeacherThesis findOneById(Long id) {
		return thesisDao.findOneById(id);
	}

	@Override
	public void deleteById(Long id) {
		thesisDao.deleteById(id);
	}

	@Override
	public List<TeacherThesis> getAllThesisById(Long Id) {
		return thesisDao.getAllThesisById(Id);
	}

	@Override
	public Page<TeacherThesis> findAllThesisByUser(int pageNum,
			int pageSize, User user) {
		Pageable dateDesc =  new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<TeacherThesis> onePage = thesisRepository.findThesisByUser(user, dateDesc);
		return onePage;
	}

}
