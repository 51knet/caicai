package com.knet51.ccweb.controllers.teacher.achievement;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.teacher.teacherCourse.TeacherCourseInfoForm;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherThesisService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class TeacherAchievePageController {
	private static final Logger logger = LoggerFactory.getLogger(TeacherAchievePageController.class);
	@Autowired
	private TeacherThesisService thesisService;
	
	@Autowired
	private TeacherProjectService projectService;
	
	@Autowired
	private TeacherPatentService patentService;
	
	@Autowired
	private TeacherHonorService honorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/admin/teacher/achievement/list")
	public String teacherAchievement(HttpSession session,Model model){
		logger.info("#### Into teacher achievement page ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		List<TeacherThesis> thesis = thesisService.getAllThesisById(user.getId());
		List<TeacherProject> project = projectService.getAllProjectById(user.getId());
		List<TeacherPatent> patent = patentService.getAllPatentById(user.getId());
		List<TeacherHonor> honor = honorService.getAllHonorById(user.getId());
		model.addAttribute("project", project);
		model.addAttribute("projectCount", project.size());
		model.addAttribute("thesis", thesis);
		model.addAttribute("thesisCount", thesis.size());
		model.addAttribute("patent", patent);
		model.addAttribute("patentCount", patent.size());
		model.addAttribute("honor", honor);
		model.addAttribute("honorCount", honor.size());
		return "admin.teacher.achievement.list";
	}
	@RequestMapping(value = "/admin/teacher/achievement/thesisInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse thesisInfoFormAjaxJson(@Valid TeacherThesisDetailInfoForm teacherThesisDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/achievement/projectInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse projectInfoFormAjaxJson(@Valid TeacherProjectDetailInfoForm teacherProjectDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/achievement/patentInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormAjaxJson(@Valid TeacherPatentDetailInfoForm teacherPatentDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/achievement/honorInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse honorInfoFormAjaxJson(@Valid TeacherHonorDetailInfoForm teacherHonorDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	/* teacher front page */
	
	@RequestMapping(value="/teacher/{teacher_id}/achievement/list")
	public String teacherFrontAchievement(@PathVariable Long teacher_id,HttpSession session,Model model){
		logger.info("#### Into teacher achievement page ####");
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		
	

		Page<TeacherHonor> pageHonor = honorService.findAllHonorByTeacher(
				0, 2, teacher);
		List<TeacherHonor> honorList = pageHonor.getContent();
		Integer honorCount = honorService.getAllHonorById(teacher_id).size();
		model.addAttribute("honorList", honorList);
		model.addAttribute("honorCount", honorCount);

		Page<TeacherPatent> pagePatent = patentService
				.findAllPatentByTeacher(0, 2, teacher);
		List<TeacherPatent> patentList = pagePatent.getContent();
		Integer patentCount = patentService.getAllPatentById(teacher_id).size();
		model.addAttribute("patentList", patentList);
		model.addAttribute("patentCount", patentCount);

		Page<TeacherThesis> pageThesis = thesisService
				.findAllThesisByTeacher(0, 2, teacher);
		List<TeacherThesis> thesisList = pageThesis.getContent();
		Integer thesisCount = thesisService.getAllThesisById(teacher_id).size();
		model.addAttribute("thesisList", thesisList);
		model.addAttribute("thesisCount", thesisCount);

		Page<TeacherProject> pageProject = projectService
				.findAllProjectByTeacher(0, 2, teacher);
		List<TeacherProject> projectList = pageProject.getContent();
		Integer projectCount = projectService.getAllProjectById(teacher_id).size();
		model.addAttribute("projectList", projectList);
		model.addAttribute("projectCount", projectCount);

		return "teacher.achievement.list";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/achievement/thesis/list")
	public String showAllThesis(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<TeacherThesis> onePage = thesisService.findAllThesisByTeacher(pageNumber, pageSize, teacher);
		model.addAttribute("page", onePage);
		return "teacher.achievement.thesis.list";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/achievement/project/list")
	public String showAllProject(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<TeacherProject> onePage = projectService.findAllProjectByTeacher(pageNumber, pageSize, teacher);
		model.addAttribute("page", onePage);
		return "teacher.achievement.project.list";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/achievement/patent/list")
	public String showAllPatent(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<TeacherPatent> onePage = patentService.findAllPatentByTeacher(pageNumber, pageSize, teacher);
		model.addAttribute("page", onePage);
		return "teacher.achievement.patent.list";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/achievement/honor/list")
	public String showAllHonor(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<TeacherHonor> onePage = honorService.findAllHonorByTeacher(pageNumber, pageSize, teacher);
		model.addAttribute("page", onePage);
		return "teacher.achievement.honor.list";
	}
}
