package com.knet51.diplomat.jpa.services.achievement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.dao.teacherAchievement.TeacherThesisDao;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.repository.achievement.ThesisRepository;
@Transactional
@Service("teacherThesisService")

public class TeacherThesisServiceImpl implements TeacherThesisService {
	@Autowired
	private TeacherThesisDao thesisDao; 
	
	@Autowired
	private ThesisRepository thesisRepository;
	
	@Override
	public TeacherThesis save(TeacherThesis teacherThesis, Teacher  teacher) {
		teacherThesis.setTeacher(teacher);
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
	public Page<TeacherThesis> findAllThesisByTeacher(int pageNum,
			int pageSize, Teacher teacher) {
		Pageable dateDesc =  new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<TeacherThesis> onePage = thesisRepository.findThesisByTeacher(teacher, dateDesc);
		return onePage;
	}

}
