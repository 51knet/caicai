package com.knet51.ccweb.controllers.teacher.announcement;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class TeacherAnnoDetailInfoController {
	
	private static Logger logger = 
			LoggerFactory.getLogger(TeacherAnnoDetailInfoController.class);
	
	@Autowired
	private AnnouncementService annoService;
	@Autowired
	private UserService userService;
	
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/new", method = RequestMethod.POST)
	public String allAnnoInfo(@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm,
			BindingResult validResult, HttpSession session,Model m){
		logger.info("####  TeacherAnnoDetailController  ####");
		
		if(validResult.hasErrors()){
			logger.info("annoDetailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/announcement/list";
		}else{
			logger.info("####  TeacherAnnoDetailController passed.  ####");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long user_id = userInfo.getId();
			User user = userService.findOne(user_id);
			String title = annoDetailInfoForm.getTitle();
			String content = annoDetailInfoForm.getContent();
			Announcement announcement = new Announcement();
			announcement.setTitle(title);
			announcement.setContent(content);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			announcement.setDate(date); 
			announcement.setUser(user);
			annoService.createAnnouncement(announcement);
			
			return "redirect:/admin/teacher/announcement/list";
		}
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/destory/{announcement_id}")
	public String teacherAnnoDele( @PathVariable Long announcement_id,HttpSession session, Model m){
		annoService.deleAnnouncementById(announcement_id);
		return "redirect:/admin/teacher/announcement/list";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/edit/edit" , method = RequestMethod.POST)
	public String teacherAnnoUpdate(@RequestParam("id") Long id,@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm,
			BindingResult validResult, HttpSession session,Model m){
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/announcement/edit/"+id;
		}else{	
			String title = annoDetailInfoForm.getTitle();
			String content = annoDetailInfoForm.getContent();
			Announcement announcement = annoService.findOneById(id);
			announcement.setTitle(title);
			announcement.setContent(content);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			announcement.setDate(date);  
			annoService.updateAnnouncement(announcement);
			return "redirect:/admin/teacher/announcement/list";
		}
	}
	
	@RequestMapping(value = "/admin/teacher/announcement/annoInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse noticeInfoFormAjaxJson(@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm, 
									BindingResult result) {
		System.out.println(result);
		return AjaxValidationEngine.process(result);
	}
}
