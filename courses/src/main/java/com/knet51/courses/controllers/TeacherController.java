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

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.controllers.defs.GlobalDefs;
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
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		//List<Teacher> teacherList = teacherService.findAllTeacher();
		Page<Teacher> teacherPage = teacherService.getAllTeacherPage(pageNumber, pageSize);
		//List<Teacher> teacher = teacherPage.getContent();
		model.addAttribute("page", teacherPage);
		return "teacher.list";
	}
	@RequestMapping(value="/teacher/{teacher_id}")
	public String showTeacherInfoById(@PathVariable Long teacher_id,Model model,HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/signin";
		} 
		Teacher teacher=teacherService.findOne(teacher_id);
		List<String> courseTypeList = courseService.getCourseTypeByTeacherId(teacher_id);
		List<TeacherCourse> teacherCourseList=courseService.getAllCourseByTeacherId(teacher_id);
		model.addAttribute("teacherCourseList", teacherCourseList);
		model.addAttribute("teacher", teacher);
		model.addAttribute("courseCount", teacherCourseList.size());
		model.addAttribute("courseTypeList", courseTypeList);
		return "teacher.teacherInfo";
	}
	
	
	
	@Transactional
	@RequestMapping(value = "/teacher/{teacher_id}/course/type")
	public String showCourseByType(@RequestParam("detail") String courseType,
			Model model,@PathVariable Long teacher_id) throws Exception {
		courseType = new String(courseType.getBytes("iso-8859-1"), "utf-8");
		Teacher teacher=teacherService.findOne(teacher_id);
		List<String> courseTypeList = courseService.getCourseTypeByTeacherId(teacher_id);
		List<TeacherCourse> courseList=courseService.getAllCourseByTeacherId(teacher_id);
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
		} else {
			model.addAttribute("teacherCourseList", courseList);
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseCount", courseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
			model.addAttribute("teacher", teacher);
		}
		return "teacher.teacherInfo";
	}
}
