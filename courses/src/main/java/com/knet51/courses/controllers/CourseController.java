package com.knet51.courses.controllers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.jpa.services.TeacherCourseService;

@Controller
public class CourseController {
	@Autowired
	private TeacherCourseService courseService;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
//	@RequestMapping(value="/course/list")
//	public String showAllCourse(Model model){
////		List<TeacherCourseBeans> tcBeanList = courseService.getAllTeacherCourseBeans();
////		model.addAttribute("courseList", tcBeanList);
//		List<TeacherCourse> courseList = courseService.findAllCourses();
//		List<String> courseTypeList = courseService.courseTypeList();
//		model.addAttribute("courseList", courseList);
//		model.addAttribute("courseCount", courseList.size());
//		model.addAttribute("courseTypeList", courseTypeList);
//		return "course.list";
//	}
	
	@RequestMapping(value="/course/list/type")
	public String showCourseByType(@RequestParam("detail") String courseType,Model model) throws Exception{
		courseType = new String(courseType.getBytes("iso-8859-1"),"utf-8"); 
		//logger.info("++++++++++++++"+courseType);
		List<TeacherCourse> courseList = courseService.findAllCourses();
		List<String> courseTypeList = courseService.courseTypeList();
	
		List<TeacherCourse> newCourseList = new ArrayList<TeacherCourse>();
		if(courseType.trim() !=null && !courseType.trim().equals("全部课程") && !courseType.trim().equals("all") ){
			for(TeacherCourse c :courseList){
				if(courseType.equals(c.getCourseType())){
					newCourseList.add(c);
				}
			}
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseList", newCourseList);
			model.addAttribute("courseCount", newCourseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
			return "course.list";
		}else{
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseCount", courseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
			return "course.list";
		}
	}

}
