package com.knet51.courses.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;

@Controller
public class TeacherController {
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private TeacherService teacherService;
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	
	@RequestMapping(value="/course/teacher/list")
	public String showAllTeacher(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		//List<Teacher> teacherList = teacherService.findAllTeacher();
		Page<Teacher> teacherPage = teacherService.getAllTeacherPage(pageNumber, pageSize);
		List<Teacher> teacher = teacherPage.getContent();
	
		model.addAttribute("page", teacherPage);
		return "course.teacher.list";
	}
}
