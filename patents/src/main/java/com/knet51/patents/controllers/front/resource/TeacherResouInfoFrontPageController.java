package com.knet51.patents.controllers.front.resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.TeacherService;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.resource.ResourceService;

@Controller
public class TeacherResouInfoFrontPageController {

	private static final Logger logger = LoggerFactory
			.getLogger(TeacherResouInfoFrontPageController.class);
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;

	/* teacher front page controller */
	/**
	 * show the teacher's resource in front page
	 * @param teacher_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/teacher/{teacher_id}/resource/list")
	public String teacherResourceList(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<CourseResource> onePage = resourceService.findAllResouByUserAndStatus(pageNumber, pageSize, user, GlobalDefs.STATUS_RESOURCE);
		model.addAttribute("page", onePage);
		return "teacher.resource.list";
	}
	
	/**
	 * show a resource detail information in front page
	 * @param teacher_id
	 * @param resource_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/teacher/{teacher_id}/resource/view/{resource_id}")
	public String teacherResourceDetail(@PathVariable Long teacher_id,@PathVariable Long resource_id,Model model){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		CourseResource resource = resourceService.findOneById(resource_id);
		model.addAttribute("resource", resource);
		return "teacher.resource.detail";
	}
}