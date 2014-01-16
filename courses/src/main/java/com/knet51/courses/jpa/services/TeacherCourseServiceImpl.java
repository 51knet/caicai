package com.knet51.courses.jpa.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.ccweb.jpa.repository.UserCourseRepository;
import com.knet51.ccweb.jpa.repository.TeacherRepository;
import com.knet51.ccweb.jpa.repository.UserRepository;
import com.knet51.ccweb.jpa.repository.course.CourseRepository;
import com.knet51.courses.beans.CourseBeans;
import com.knet51.courses.beans.TeacherCourseBeans;
import com.knet51.courses.controllers.defs.GlobalDefs;

@Transactional
@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserCourseRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<String> getAllSchool() {
		return courseRepository.getSchool();
	}

	@Override
	public List<Teacher> getAllCourseTeacher(String schoolName) {
		return courseRepository.showCourseTeacher(schoolName);
	}

	@Override
	public Map<Teacher, List<Course>> tcmap() {
		Map<Teacher,List<Course>> tcmap = new HashMap<Teacher,List<Course>>();
		List<Teacher> teacherList = teacherRepository.findAll();
		for(int i=0;i<teacherList.size();i++){
			List<Course> courseList = courseRepository.getAllCourseById(teacherList.get(i).getId());
			if(courseList.size()>0){
				tcmap.put(teacherList.get(i), courseList);
			}
		}
		return tcmap;
	}

	@Override
	public List<TeacherCourseBeans> getAllTeacherCourseBeans() {
		List<TeacherCourseBeans> tcBeansList = new ArrayList<TeacherCourseBeans>();
		List<Course> courseList = courseRepository.findAll();
		for(int i=0;i<courseList.size();i++){
			Teacher teacher = teacherRepository.findOne(courseList.get(i).getUser().getId());
			TeacherCourseBeans tcBeans = new TeacherCourseBeans(teacher, courseList.get(i));
			tcBeansList.add(tcBeans);
		}
		return tcBeansList;
	}

	@Override
	public Course findOneById(Long id) {	 
		return courseRepository.findOne(id);
	}

	@Override
	public Course createTeacherCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course updateTeacherCourse(Course course) {
		return courseRepository.updateTeacherCourseDetail(course);
	}

	@Override
	public void deleTeacherCourse(Long teacher_id) {
		courseRepository.delete(teacher_id);
	}

	@Override
	public Page<Course> findAllCourseByUser(int pageNum,
			int pageSize,User user) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<Course> onePage = courseRepository.findTeacherCourseByUser(user, dateDesc);
		return onePage;
	}
	@Override
	public Page<Course> findAllCourse(int pageNum,int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<Course> onePage = courseRepository.findAll(dateDesc);
		return onePage;
	}
	
	@Override
	public List<String> courseTypeList() {
		return courseRepository.getCourseType();
	}

	/**
	 * 查询通过教师ID教师课程
	 */
	@Override
	public List<Course> getAllCourseByTeacherId(Long teacher_id) {
		List<Course> list=new ArrayList<Course>();
		User user = userRepository.findOne(teacher_id);
		try {
			list=courseRepository.findTeacherCourseByUserAndStatusAndForbiddenIsNullAndPublish(user, GlobalDefs.STATUS_CCWEB_COURSES,GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<CourseBeans> getAllCourseBeans() {
		List<CourseBeans> courseBeansList = new ArrayList<CourseBeans>();
		Sort sort = new Sort(Direction.DESC, "id");
		List<Course> teacherCourseList = courseRepository.findTeacherCourseByStatusAndPublishAndForbiddenIsNull(GlobalDefs.STATUS_CCWEB_COURSES,GlobalDefs.PUBLISH_NUM_ADMIN_FRONT,sort);
		for(int i=0;i<teacherCourseList.size();i++){
			Double avgMark = commentRepository.getMark(teacherCourseList.get(i).getId());
			List<UserCourse> listComment=commentRepository.findByTeachercourseid(teacherCourseList.get(i).getId());
			Integer userCount = listComment.size();
			CourseBeans courseBeans = new CourseBeans(userCount, avgMark, teacherCourseList.get(i));
			courseBeansList.add(courseBeans);
		}
		return courseBeansList;
	}

	@Override
	public CourseBeans getCourseBeansById(Long course_id) {
		Course course = courseRepository.findOne(course_id);
		Double avgMark = commentRepository.getMark(course_id);
		List<UserCourse> listComment=commentRepository.findByTeachercourseid(course_id);
		Integer userCount = listComment.size();
		CourseBeans courseBeans = new CourseBeans();
		courseBeans.setCourseMark(avgMark);
		courseBeans.setTeacherCourse(course);
		courseBeans.setUserCount(userCount);
		return courseBeans;
	}

	@Override
	public List<String> getCourseTypeByTeacherId(Long teacher_id) {
		return courseRepository.getCourseTypeByTeacherId(teacher_id);
	}

	@Override
	public List<Course> getCourseByUserId(Long user_id) {
		List<UserCourse> commentList = commentRepository.findUserCourseByUserid(user_id);
		List<Course> courseList = new ArrayList<Course>();
		for(int i=0;i<commentList.size();i++){
			Course course = new Course();
			course = courseRepository.findOne(commentList.get(i).getTeachercourseid());
			courseList.add(course);
		}
		return courseList;
	}

	@Override
	public List<Course> findAllCourses() {
		Sort sort = new Sort(Direction.DESC, "id");
		List<Course> courseList = courseRepository.findTeacherCourseByStatusAndPublishAndForbiddenIsNull(GlobalDefs.STATUS_CCWEB_COURSES,GlobalDefs.PUBLISH_NUM_ADMIN_FRONT,sort);
		return courseList;
	}

}
