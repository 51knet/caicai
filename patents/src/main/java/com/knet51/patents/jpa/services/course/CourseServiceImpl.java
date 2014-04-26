package com.knet51.patents.jpa.services.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.repository.TeacherRepository;
import com.knet51.ccweb.jpa.repository.UserRepository;
import com.knet51.ccweb.jpa.repository.course.CourseRepository;
import com.knet51.patents.controllers.common.defs.GlobalDefs;

@Transactional
@Service("courseService")
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private UserRepository userRepository;

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
	public List<Course> getAllTeacherCourseByUseridAndPublish(Long teacher_id,
			Integer publish) {
		User user = userRepository.findOne(teacher_id);
		return courseRepository
				.findTeacherCourseByUserAndPublish(user, publish);
	}

	@Override
	public void deleTeacherCourse(Long course_id) {
		courseRepository.delete(course_id);
	}

	@Override
	public Page<Course> findAllCourseByUser(int pageNum, int pageSize, User user) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC,
				"id");
		Page<Course> onePage = courseRepository.findTeacherCourseByUser(user,
				dateDesc);
		return onePage;
	}

	// @Override
	// public List<CourseBeans> getAllCourseBeans() {
	// // FIXME this is stupid to depend the CourseBeans on this layer
	// //return teacherCourseRepository.listAllCourseBeans(teacher_id);
	// return Collections.emptyList();
	// }

	@Override
	public List<String> getAllSchool() {
		return courseRepository.getSchool();
	}

	@Override
	public List<Teacher> getAllCourseTeacher(String schoolName) {
		return courseRepository.showCourseTeacher(schoolName);
	}

	@Override
	public Page<Course> findTeacherCourseByUserAndPublish(int pageNum,
			int pageSize, User user, Integer publish) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC,
				"id");
		Page<Course> onePage = courseRepository
				.findTeacherCourseByUserAndPublishAndForbiddenIsNull(user,
						publish, dateDesc);
		return onePage;
	}

	@Override
	public Page<Course> findTeacherCourseByUserAndPublishGreaterThan(
			int pageNum, int pageSize, User user, Integer publish) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC,
				"id");
		Page<Course> onePage = courseRepository
				.findTeacherCourseByUserAndForbiddenIsNullAndPublishGreaterThan(
						user, publish, dateDesc);
		return onePage;
	}

	@Override
	public Course getTeacherCourseByCourseName(String cousername, Long teacherId) {
		Course course = courseRepository.getTeacherCourseByCourseName(
				cousername, teacherId);
		return course;
	}

	@Override
	public Page<Course> findAllCourse(int pageNum, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC,
				"id");
		Page<Course> onePage = courseRepository.findAll(dateDesc);
		return onePage;
	}

	@Override
	public Page<Course> findTeacherCourseByUserAndPublishAndCType(int pageNum,
			int pageSize, User user, Integer publish, CourseType cType) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC,
				"id");
		Page<Course> onePage = courseRepository
				.findTeacherCourseByUserAndPublishAndCType(user, publish,
						cType, dateDesc);
		return onePage;
	}

	@Override
	public List<Course> findCourseByUserAndPublishGreaterThanForSuperAdmin(
			User user, Integer publish) {
		return courseRepository.findTeacherCourseByUserAndPublishGreaterThan(
				user, publish);
	}

	@Override
	public Page<Course> findCourseByUserAndPublishGreaterThanForSuperAdmin(
			User user, Integer publish, int pageNum, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC,
				"id");
		Page<Course> onePage = courseRepository
				.findTeacherCourseByUserAndPublishGreaterThan(user, publish,
						dateDesc);
		return onePage;
	}

	@Override
	public Page<Course> findCourseByPublishGreaterThanForSuperAdmin(
			Integer publish, int pageNum, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC,
				"id");
		Page<Course> onePage = courseRepository.findAllByPublishGreaterThan(
				publish, dateDesc);
		return onePage;
	}

	@Override
	public List<Course> findAllForAdmin() {
		return courseRepository
				.findAllByPublishGreaterThan(GlobalDefs.PUBLISH_NUM_DELETE);
	}

	@Override
	public List<Course> findAllPublish() {
		return courseRepository
				.findAllByPublishGreaterThan(GlobalDefs.PUBLISH_NUM_ADMIN);
	}

}
