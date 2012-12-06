package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.beans.CourseBeans;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.repository.TeacherCourseRepository;
@Transactional
@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService {
	@Autowired
	private TeacherCourseRepository teacherCourseRepository;

	@Override
	public TeacherCourse findOneById(Long id) {	 
		return teacherCourseRepository.findOne(id);
	}

	@Override
	public TeacherCourse createTeacherCourse(TeacherCourse teacherCourse) {
		return teacherCourseRepository.save(teacherCourse);
	}

	@Override
	public TeacherCourse updateTeacherCourse(TeacherCourse teacherCourse) {
		return teacherCourseRepository.updateTeacherCourseDetail(teacherCourse);
	}

	@Override
	public List<TeacherCourse> getAllTeacherCourseById(Long teacher_id) {
		return teacherCourseRepository.getAllCourseById(teacher_id);
	}

	@Override
	public void deleTeacherCourse(Long teacher_id) {
		teacherCourseRepository.delete(teacher_id);
	}

	@Override
	public Page<TeacherCourse> findAllCourseByTeacher(int pageNum, int pageSize,
			Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = teacherCourseRepository.findTeacherCourseByTeacher(teacher, dateDesc);
		return onePage;
	}

	@Override
	public List<CourseBeans> getAllCourseBeans(Long teacher_id) {
		// TODO Auto-generated method stub
		return teacherCourseRepository.listAllCourseBeans(teacher_id);
	}

	@Override
	public List<String> getAllSchool() {
		return teacherCourseRepository.getSchool();
	}

	@Override
	public List<Teacher> getAllCourseTeacher(String schoolName) {
		return teacherCourseRepository.showCourseTeacher(schoolName);
	}

}
