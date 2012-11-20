package com.knet51.ccweb.controllers.teacher.teacherCourse;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;

@Controller
public class TeacherCourseInfoPageController {
	private static final Logger logger = 
			LoggerFactory.getLogger(TeacherCourseInfoPageController.class);
	@Autowired
	private TeacherCourseService teacherCourseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CourseResourceService courseResourceService;
	
	@RequestMapping(value="/admin/teacher/course/list")
	public String teacherCourseInfo(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="2") int pageSize){
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Teacher teacher = teacherService.findOne(userInfo.getId());
		Page<TeacherCourse> onePage =teacherCourseService.findAllCourseByTeacher(pageNumber, pageSize, teacher);
		model.addAttribute("page", onePage);
		return "admin.teacher.course.list";
	}
	

	
	@RequestMapping(value="/admin/teacher/course/view/{course_id}")
	public String detailCourseInfo(@PathVariable Long course_id,Model m){
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(course_id);
		m.addAttribute("course", course);
		m.addAttribute("resourceList",resourceList);
		return "admin.teacher.course.view";
	}
	
	@RequestMapping(value="/admin/teacher/course/edit/{course_id}")
	public String updateCourseInfo(@PathVariable Long course_id,Model m){
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		m.addAttribute("course", course);
		return "admin.teacher.course.edit";
	}
	
	@RequestMapping(value="/admin/teacher/{course_id}/resource/new")
	public String addTeacherCourseResource(Model model,@PathVariable Long course_id){
		model.addAttribute("id",course_id);
		return "admin.teacher.course.resource.new";
	}
	
	/*@RequestMapping(value="/admin/teacher/teacherCourse/detailCourse")
	public String detailCourse(@RequestParam("id") Long id, Model m){
		TeacherCourse course = teacherCourseService.findOneById(id);
		List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(id);
		m.addAttribute("course", course);
		m.addAttribute("resourceList",resourceList);
		return "admin.teacher.teacherCourse.detailCourse";
	}*/
}
