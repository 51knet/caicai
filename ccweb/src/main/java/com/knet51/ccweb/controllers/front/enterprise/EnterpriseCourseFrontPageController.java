package com.knet51.ccweb.controllers.front.enterprise;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseTypeService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class EnterpriseCourseFrontPageController {
	private static Logger logger = LoggerFactory
			.getLogger(EnterpriseCourseFrontPageController.class);
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseTypeService courseTypeService;
	
	@RequestMapping(value = "/enterprise/course/view", method = RequestMethod.POST)
	public String detailCourse(@RequestParam("enterpriseId") Long enterprise_id,
			@RequestParam("courseId") Long course_id,
			@RequestParam("coursepwd") String pwd, Model model) {
		TeacherCourse course = courseService.findOneById(course_id);
			User user = userService.findOne(enterprise_id);
			UserInfo userInfo = new UserInfo(user);
			logger.debug(userInfo.toString());
			model.addAttribute("teacherInfo", userInfo);
			model.addAttribute("teacher_id", enterprise_id);
			model.addAttribute("course", course);
			List<CourseType> cTypeList = courseTypeService.findAll();
			logger.info("===============" + cTypeList.size());
			model.addAttribute("cTypeList", cTypeList);
			List<TeacherCourse> courseList = courseService
					.getAllTeacherCourseByUseridAndPublish(enterprise_id,
							GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseCount", courseList.size());
			return "enterprise.course.view";
	}
	
	/**
	 * show enterprise course list
	 * 
	 * @param id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/enterprise/{id}/course/list")
	public String getAllEnterpriseCourse(
			@PathVariable Long id,
			Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", id);
		Page<TeacherCourse> onePage = courseService
				.findTeacherCourseByUserAndPublish(pageNumber, pageSize, user,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("page", onePage);

		List<TeacherCourse> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);

		return "enterprise.course.list";
	}

	/**
	 * filter the course by course type
	 * 
	 * @param teacher_id
	 * @param type_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/enterprise/{enterprise_id}/course/type/{type_id}")
	public String filterCourseByType(
			@PathVariable Long enterprise_id,
			@PathVariable Long type_id,
			Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
		User user = userService.findOne(enterprise_id);
		// Teacher teacher = teacherService.findOne(enterprise_id);
		CourseType cType = courseTypeService.findOneById(type_id);
		UserInfo userInfo = new UserInfo(user);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);
		Page<TeacherCourse> onePage = courseService
				.findTeacherCourseByUserAndPublishAndCType(pageNumber,
						pageSize, user, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT,
						cType);
		model.addAttribute("page", onePage);

		List<TeacherCourse> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(enterprise_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		return "enterprise.course.list";
	}
	/**
	 * search course by courseName or min price or max price
	 * @param courseName
	 * @param min_price
	 * @param max_price
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/enterprise/searchCourse")
	public String findCourse(@RequestParam("courseName") String courseName,@RequestParam("minPrice") Long min_price,
			@RequestParam("maxPrice")Long max_price,@RequestParam("teacher_id")Long user_id,Model model){
		String cName = courseName.trim();
		User user = userService.findOne(user_id);
		List<TeacherCourse> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(user_id,GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		List<TeacherCourse> newCourseList = null;
		if(cName !=null ){}
		return "enterprise.course.list";
	}
	
}
