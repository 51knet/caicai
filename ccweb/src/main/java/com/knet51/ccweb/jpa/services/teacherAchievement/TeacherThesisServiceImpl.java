package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.dao.teacherAchievement.TeacherThesisDao;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
@Transactional
@Service("teacherThesisService")

public class TeacherThesisServiceImpl implements TeacherThesisService {
	@Autowired
	private TeacherThesisDao thesisDao; 
	
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

}
