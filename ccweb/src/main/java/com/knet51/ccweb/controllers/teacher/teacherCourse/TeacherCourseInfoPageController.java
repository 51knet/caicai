package com.knet51.ccweb.controllers.teacher.teacherCourse;

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

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;

@Controller
public class TeacherCourseInfoPageController {
	private static final Logger logger = 
			LoggerFactory.getLogger(TeacherCourseInfoPageController.class);
	@Autowired
	private TeacherCourseService teacherCourseService;
	
	@Autowired
	private CourseResourceService courseResourceService;
	
	@RequestMapping(value="/admin/teacher/teacherCourse/detail")
	public String teacherCourseInfo(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="2") int pageSize){
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Teacher teacher = userInfo.getTeacher();
		Page<TeacherCourse> onePage =teacherCourseService.findAllCourseByTeacher(pageNumber, pageSize, teacher);
		model.addAttribute("page", onePage);
		return "admin.teacher.teacherCourse.detail";
	}
	
	@RequestMapping(value="/admin/teacher/teacherCourse/add")
	public String teacherCourseAdd(){
		return "admin.teacher.teacherCourse.add";
	}
	
	@RequestMapping(value="/admin/teacher/teacherCourse/detailOne")
	public String detailCourseInfo(@RequestParam("id") Long id, Model m){
		TeacherCourse course = teacherCourseService.findOneById(id);
		m.addAttribute("course", course);
		return "admin.teacher.teacherCourse.detailCourseInfo";
	}
	
	@RequestMapping(value="/admin/teacher/teacherCourse/addResourcePage")
	public String addTeacherCourseResource(@RequestParam("id") Long id, Model m){
		m.addAttribute("id",id);
		return "admin.teacher.teacherCourse.addCourseResource";
	}
	
	@RequestMapping(value="/admin/teacher/teacherCourse/detailCourse")
	public String detailCourse(@RequestParam("id") Long id, Model m){
		TeacherCourse course = teacherCourseService.findOneById(id);
		List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(id);
		m.addAttribute("course", course);
		m.addAttribute("resourceList",resourceList);
		return "admin.teacher.teacherCourse.detailCourse";
	}
}
