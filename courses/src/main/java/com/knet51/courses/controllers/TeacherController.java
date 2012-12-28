package com.knet51.courses.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;

@Controller
public class TeacherController {
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private TeacherService teacherService;
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	
	@RequestMapping(value="/teacher/list")
	public String showAllTeacher(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		//List<Teacher> teacherList = teacherService.findAllTeacher();
		Page<Teacher> teacherPage = teacherService.getAllTeacherPage(pageNumber, pageSize);
		//List<Teacher> teacher = teacherPage.getContent();
		model.addAttribute("page", teacherPage);
		return "teacher.list";
	}
	@RequestMapping(value="/teacher/{id}")
	public String showTeacherInfoById(@PathVariable Long id,Model model){
		Teacher teacher=teacherService.findOne(id);
		List<String> courseTypeList = courseService.getCourseTypeByTeacherId(id);
		List<TeacherCourse> teacherCourseList=courseService.getAllCourseById(id);
		model.addAttribute("teacherCourseList", teacherCourseList);
		model.addAttribute("teacher", teacher);
		model.addAttribute("courseCount", teacherCourseList.size());
		model.addAttribute("courseTypeList", courseTypeList);
		return "teacher.teacherInfo";
	}
	
	
	
	@Transactional
	@RequestMapping(value = "/teacher/{id}/course/type")
	public String showCourseByType(@RequestParam("detail") String courseType,
			Model model,@PathVariable Long id) throws Exception {
		courseType = new String(courseType.getBytes("iso-8859-1"), "utf-8");
		Teacher teacher=teacherService.findOne(id);
		List<String> courseTypeList = courseService.getCourseTypeByTeacherId(id);
		List<TeacherCourse> courseList=courseService.getAllCourseById(id);
		List<TeacherCourse> newCourseList = new ArrayList<TeacherCourse>();
		if (courseType.trim() != null && !courseType.trim().equals("全部课程")) {
			for (TeacherCourse c : courseList) {
				if (courseType.equals(c.getCourseType())) {
					newCourseList.add(c);
				}
			}
			model.addAttribute("courseType", courseType);
			model.addAttribute("teacherCourseList", newCourseList);
			model.addAttribute("courseCount", newCourseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
			model.addAttribute("teacher", teacher);
			return "teacher.teacherInfo";
		} else {
			model.addAttribute("teacherCourseList", courseList);
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseCount", courseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
			model.addAttribute("teacher", teacher);
			return "teacher.teacherInfo";
		}
	}
}
