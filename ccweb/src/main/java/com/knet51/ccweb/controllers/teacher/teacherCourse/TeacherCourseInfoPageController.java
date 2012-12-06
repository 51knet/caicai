package com.knet51.ccweb.controllers.teacher.teacherCourse;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import com.knet51.ccweb.beans.CourseBeans;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;

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
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/teacher/course/list")
	public String teacherCourseInfo(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Teacher teacher = teacherService.findOne(userInfo.getId());
		List<CourseBeans> cb = teacherCourseService.getAllCourseBeans(userInfo.getId());
		Page<TeacherCourse> onePage =teacherCourseService.findAllCourseByTeacher(pageNumber, pageSize, teacher);
		model.addAttribute("page", onePage);
		return "admin.teacher.course.list";
	}
	
	@RequestMapping(value="/admin/teacher/course/new")
	public String courseAdd(){
		return "admin.teacher.course.new";
	}
	

	
	@RequestMapping(value="/admin/teacher/course/view/{course_id}")
	public String detailCourseInfo(@PathVariable Long course_id,Model model){
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(course_id);
		model.addAttribute("course", course);
		model.addAttribute("resourceList",resourceList);
		return "admin.teacher.course.view";
	}
	
	@RequestMapping(value="/admin/teacher/course/edit/{course_id}")
	public String updateCourseInfo(@PathVariable Long course_id,Model model){
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		model.addAttribute("course", course);
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
	
	@RequestMapping(value="/teacher/{teacher_id}/course/list")
	public String getAllTeacherCourse(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<TeacherCourse> onePage = teacherCourseService.findAllCourseByTeacher(pageNumber, pageSize, teacher);
		model.addAttribute("page", onePage);
		return "teacher.course.list";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/course/view/{course_id}")
	public String detailCourse(@PathVariable Long teacher_id, @PathVariable Long course_id,Model model){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(course_id);
		model.addAttribute("course", course);
		model.addAttribute("resourceList",resourceList);
		model.addAttribute("resourceCount", resourceList.size());
		return "teacher.course.view";
	}
	
	
	@RequestMapping(value="/admin/teacher/allCourse/list")
	public String teacherAllCourseInfo(HttpSession session,Model model ){
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		//Teacher teacher = teacherService.findOne(userInfo.getId());
		List<CourseBeans> courseBean = teacherCourseService.getAllCourseBeans(userInfo.getId());
		model.addAttribute("cb", courseBean);
		return "admin.teacher.allCourse.list";
	}
	
	@RequestMapping(value="/admin/teacher/course/all/list")
	public String teacherAllCourseList(HttpSession session,Model model,@RequestParam("school") String schoolName) throws Exception{
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<String> school = teacherCourseService.getAllSchool();
		schoolName = new String(schoolName.getBytes("iso-8859-1"),"utf-8"); 
		logger.info("+++++++++++++++++++++++"+schoolName+"++++++++++++++++++++");
		List<CourseBeans> courseBean = teacherCourseService.getAllCourseBeans(userInfo.getId());
		List<CourseBeans> newCourseBeans = new ArrayList<CourseBeans>();
		if(schoolName.trim() !=null && !schoolName.trim().equals("all")){
			List<Teacher> teacherList = teacherCourseService.getAllCourseTeacher(schoolName);
			for(CourseBeans c :courseBean){
				if(schoolName.equals(c.getTeacher().getCollege())){
					newCourseBeans.add(c);
				}
			}
			model.addAttribute("schoolName", schoolName);
			model.addAttribute("cb", newCourseBeans);
			model.addAttribute("school", school);
			model.addAttribute("teacherList", teacherList);
			return "admin.teacher.course.all.list";
		}else if(schoolName.trim().equals("all")){
			model.addAttribute("cb", courseBean);
			model.addAttribute("school", school);
			return "admin.teacher.course.all.list";
		}else{
			model.addAttribute("cb", courseBean);
			model.addAttribute("school", school);
			return "admin.teacher.course.all.list";
		}
		
		
	}
}
