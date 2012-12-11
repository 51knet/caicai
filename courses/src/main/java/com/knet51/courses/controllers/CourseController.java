package com.knet51.courses.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.beans.TeacherCourseBeans;
import com.knet51.courses.jpa.services.TeacherCourseService;

@Controller
public class CourseController {
	@Autowired
	private TeacherCourseService courseService;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@RequestMapping(value="/course/list")
	public String showAllCourse(Model model){
//		List<TeacherCourseBeans> tcBeanList = courseService.getAllTeacherCourseBeans();
//		model.addAttribute("courseList", tcBeanList);
		List<TeacherCourse> courseList = courseService.findAllCourses();
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		return "course.list";
	}

}
