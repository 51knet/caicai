package com.knet51.ccweb.controllers.front.teacher.announcement;


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
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.announcement.AnnouncementService;

@Controller
public class TeacherAnnoInfoFrontPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherAnnoInfoFrontPageController.class); 
	@Autowired
	private AnnouncementService annoService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	/* teacher front page */
	@RequestMapping(value="/teacher/{teacher_id}/announcement/list")
	public String annoList(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<Announcement> page = annoService.findAllAnnoByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return "teacher.announcement.list";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/announcement/view/{anno_id}")
	public String detailAnno(@PathVariable Long teacher_id,@PathVariable Long anno_id ,Model model){
		
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Announcement announcement = annoService.findOneById(anno_id);
		model.addAttribute("announcement", announcement);
		return "teacher.announcement.detail";
	}
}
