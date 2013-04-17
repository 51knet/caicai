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
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.CourseTypeService;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserCourseService;

@Controller
public class TeacherController {
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseTypeService courseTypeService;
	@Autowired
	private UserCourseService userCourseService;
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	/**
	 * show all teacher
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/teacher/list")
	public String showAllTeacher(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		//List<Teacher> teacherList = teacherService.findAllTeacher();
		Page<Teacher> teacherPage = null;
		List<Teacher> teacherList = null;
		teacherList = teacherService.findTeacherByIsEnterprise();
		teacherPage = teacherService.findAllTeacher(pageNumber, pageSize);
		//List<Teacher> teacher = teacherPage.getContent();
		model.addAttribute("page", teacherPage);
		model.addAttribute("teacherList", teacherList);
		UserInfo currentUser = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(currentUser != null){
			List<UserCourse> userCourseList = userCourseService.findUserCourseByUserid(currentUser.getId());
			List<TeacherCourse> userCourse = new ArrayList<TeacherCourse>();
			for (int i = 0; i < userCourseList.size(); i++) {
				TeacherCourse teacherCourse = courseService.findOneById(userCourseList.get(i).getTeachercourseid());
				userCourse.add(teacherCourse);
			}
			
			model.addAttribute("userCourse", userCourse);
			model.addAttribute("userCourseCount", userCourse.size());
		}
		return "teacher.list";
	}
	
	@RequestMapping(value="/enterprise/list")
	public String showAllEnterPrise(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		Page<Teacher> enterprisePage = null;
		List<Teacher> enterpriseList = null;
		enterpriseList = teacherService.findEnterpriseByisEnterprise("1");
		enterprisePage = teacherService.findAllEnterpriseByisEnterprise(pageNumber, pageSize, "1");

		model.addAttribute("page", enterprisePage);
		model.addAttribute("enterpriseList", enterpriseList);
		UserInfo currentUser = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(currentUser != null){
			List<UserCourse> userCourseList = userCourseService.findUserCourseByUserid(currentUser.getId());
			List<TeacherCourse> userCourse = new ArrayList<TeacherCourse>();
			for (int i = 0; i < userCourseList.size(); i++) {
				TeacherCourse teacherCourse = courseService.findOneById(userCourseList.get(i).getTeachercourseid());
				userCourse.add(teacherCourse);
			}
			
			model.addAttribute("userCourse", userCourse);
			model.addAttribute("userCourseCount", userCourse.size());
		}
		return "enterprise.list";
	}
	/**
	 * show teacher's courses by teacher_id
	 * @param teacher_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/teacher/{teacher_id}")
	public String showTeacherInfoById(@PathVariable Long teacher_id,Model model){
		/*UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/signin";
		} */
		Teacher teacher=teacherService.findOne(teacher_id);
		List<String> courseTypeList = courseService.getCourseTypeByTeacherId(teacher_id);
		List<TeacherCourse> teacherCourseList=courseService.getAllCourseByTeacherId(teacher_id);
		model.addAttribute("teacherCourseList", teacherCourseList);
		model.addAttribute("teacher", teacher);
		model.addAttribute("courseCount", teacherCourseList.size());
		model.addAttribute("courseTypeList", courseTypeList);
		return "teacher.teacherInfo";
	}
	
	
	/**
	 * filter the courses by course type in teacher shop
	 * @param courseType
	 * @param model
	 * @param teacher_id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value = "/teacher/{teacher_id}/course/type")
	public String showCourseByType(@RequestParam("detail") String courseType,
			Model model,@PathVariable Long teacher_id) throws Exception {
		courseType = new String(courseType.getBytes("iso-8859-1"), "utf-8");
		Teacher teacher=teacherService.findOne(teacher_id);
		//List<String> courseTypeList = courseService.getCourseTypeByTeacherId(teacher_id);
		List<TeacherCourse> courseList=courseService.getAllCourseByTeacherId(teacher_id);
		List<CourseType> typeList = courseTypeService.findAll();
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
			model.addAttribute("courseTypeList", typeList);
			model.addAttribute("teacher", teacher);
		} else {
			model.addAttribute("teacherCourseList", courseList);
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseCount", courseList.size());
			model.addAttribute("courseTypeList", typeList);
			model.addAttribute("teacher", teacher);
		}
		return "teacher.teacherInfo";
	}
}
