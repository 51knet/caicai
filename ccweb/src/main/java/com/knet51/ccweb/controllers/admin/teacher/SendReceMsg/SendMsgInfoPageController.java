package com.knet51.ccweb.controllers.admin.teacher.sendReceMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.announcement.AnnouncementService;
@Controller
public class SendMsgInfoPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(SendMsgInfoPageController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@RequestMapping(value="/sendmessage")
	public String sendMsgPage(@RequestParam("uid") Long userId,Model model){
		logger.info("#### Into send message page ####");
		User user;
		UserInfo userInfo;
		Announcement announcement;
		user = userService.findOne(userId);
		announcement = announcementService.findLatestByUid(userId);
		userInfo = new UserInfo(user);
		
		model.addAttribute("userInfoModel", userInfo);
		model.addAttribute("announcement", announcement);
		model.addAttribute("photoUrl", userInfo.getPhotoUrl());
		model.addAttribute("uid", userInfo.getId());
		model.addAttribute("name", userInfo.getName());
		model.addAttribute("gender", userInfo.getGender());
		//System.out.println(userId);
		//model.addAttribute("uid",userId);
		
		
		//return "redirect:/teacher/" + userId;
		return "admin.sendmessage";
	}
	
}
