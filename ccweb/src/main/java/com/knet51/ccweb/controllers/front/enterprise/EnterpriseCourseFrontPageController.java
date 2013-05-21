package com.knet51.ccweb.controllers.front.enterprise;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
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
	private TeacherCourseService teacherCourseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseTypeService courseTypeService;
	
	@RequestMapping(value = "/enterprise/course/view", method = RequestMethod.POST)
	public String detailCourse(@RequestParam("enterpriseId") Long enterprise_id,
			@RequestParam("courseId") Long course_id,
			@RequestParam("coursepwd") String pwd, Model model) {
		TeacherCourse course = teacherCourseService.findOneById(course_id);
			User user = userService.findOne(enterprise_id);
			UserInfo userInfo = new UserInfo(user);
			logger.debug(userInfo.toString());
			model.addAttribute("teacherInfo", userInfo);
			model.addAttribute("teacher_id", enterprise_id);
			model.addAttribute("course", course);
			List<CourseType> cTypeList = courseTypeService.findAll();
			logger.info("===============" + cTypeList.size());
			model.addAttribute("cTypeList", cTypeList);
			List<TeacherCourse> courseList = teacherCourseService
					.getAllTeacherCourseByTeacheridAndPublish(enterprise_id,
							GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseCount", courseList.size());
			return "enterprise.course.view";
	}
	
}
