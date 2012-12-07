package com.knet51.courses.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.courses.jpa.services.TeacherCourseService;

@Controller
public class CourseController {
	@Autowired
	private TeacherCourseService courseService;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@RequestMapping(value="/course/list")
	public String showAllCourse(){
		return null;
	}

}
