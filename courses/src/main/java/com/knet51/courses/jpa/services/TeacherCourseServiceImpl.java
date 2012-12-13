package com.knet51.courses.jpa.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.repository.TeacherCourseRepository;
import com.knet51.ccweb.jpa.repository.TeacherRepository;
import com.knet51.courses.beans.TeacherCourseBeans;

@Transactional
@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherCourseRepository courseRepository;

	@Override
	public List<String> getAllSchool() {
		return courseRepository.getSchool();
	}

	@Override
	public List<Teacher> getAllCourseTeacher(String schoolName) {
		return courseRepository.showCourseTeacher(schoolName);
	}

	@Override
	public Map<Teacher, List<TeacherCourse>> tcmap() {
		Map<Teacher,List<TeacherCourse>> tcmap = new HashMap<Teacher,List<TeacherCourse>>();
		List<Teacher> teacherList = teacherRepository.findAll();
		for(int i=0;i<teacherList.size();i++){
			List<TeacherCourse> courseList = courseRepository.getAllCourseById(teacherList.get(i).getId());
			if(courseList.size()>0){
				tcmap.put(teacherList.get(i), courseList);
			}
		}
		return tcmap;
	}

	@Override
	public List<TeacherCourseBeans> getAllTeacherCourseBeans() {
		List<TeacherCourseBeans> tcBeansList = new ArrayList<TeacherCourseBeans>();
		List<TeacherCourse> courseList = courseRepository.findAll();
		for(int i=0;i<courseList.size();i++){
			Teacher teacher = teacherRepository.findOne(courseList.get(i).getTeacher().getId());
			TeacherCourseBeans tcBeans = new TeacherCourseBeans(teacher, courseList.get(i));
			tcBeansList.add(tcBeans);
		}
		return tcBeansList;
	}

	@Override
	public TeacherCourse findOneById(Long id) {	 
		return courseRepository.findOne(id);
	}

	@Override
	public TeacherCourse createTeacherCourse(TeacherCourse teacherCourse) {
		return courseRepository.save(teacherCourse);
	}

	@Override
	public TeacherCourse updateTeacherCourse(TeacherCourse teacherCourse) {
		return courseRepository.updateTeacherCourseDetail(teacherCourse);
	}

	@Override
	public void deleTeacherCourse(Long teacher_id) {
		courseRepository.delete(teacher_id);
	}

	@Override
	public Page<TeacherCourse> findAllCourseByTeacher(int pageNum,
			int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByTeacher(teacher, dateDesc);
		return onePage;
	}
	@Override
	public Page<TeacherCourse> findAllCourse(int pageNum,int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findAll(dateDesc);
		return onePage;
	}

}
