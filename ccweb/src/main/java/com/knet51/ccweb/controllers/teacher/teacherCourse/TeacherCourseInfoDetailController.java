package com.knet51.ccweb.controllers.teacher.teacherCourse;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.TeacherCourseService;


@Controller
public class TeacherCourseInfoDetailController {
	private static Logger logger = 
			LoggerFactory.getLogger(TeacherCourseInfoDetailController.class);
	
	@Autowired
	private TeacherCourseService courseService;
	
	
	@RequestMapping(value="/admin/teacher/teacherCourse/addCourseInfo")
	public String TeacherCourseAddInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session){
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "admin.teacher.teacherCourse.add";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			Teacher teacher = userInfo.getTeacher();
			TeacherCourse course = new TeacherCourse();
			String courseName = courseInfoForm.getCourseName();
			String courseDesc = courseInfoForm.getCourseDesc();
			course.setCourseName(courseName);
			course.setCourseDesc(courseDesc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			course.setCourseDate(date);
			course.setTeacher(teacher);
			courseService.createTeacherCourse(course);
			return "redirect:/admin/teacher/teacherCourse/detail";
		}
	
	}
	
	@RequestMapping(value="/admin/teacher/teacherCourse/updateCourseInfo")
	public String TeacherCourseUpdateInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session,@RequestParam("id") Long id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			System.out.println(id);
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "/admin/teacher/teacherCourse/detailOne?id="+id;
		}else{
			TeacherCourse course = new TeacherCourse();
			String courseName = courseInfoForm.getCourseName();
			String courseDesc = courseInfoForm.getCourseDesc();
			course.setCourseName(courseName);
			course.setCourseDesc(courseDesc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			course.setCourseDate(date);
			courseService.updateTeacherCourse(course);
			return "redirect:/admin/teacher/teacherCourse/detail";
		}
	
	}
	
	@RequestMapping(value="/admin/teacher/teacherCourse/deleCourse")
	public String TeacherCourseDele( HttpSession session,@RequestParam("id") Long id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
			courseService.deleTeacherCourse(id);
			return "redirect:/admin/teacher/teacherCourse/detail";
	}
	
	
}
