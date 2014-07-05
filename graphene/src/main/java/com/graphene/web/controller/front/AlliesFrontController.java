package com.graphene.web.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.jpa.entity.announcement.Announcement;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;
import com.graphene.web.service.announcement.AnnouncementService;

@Controller
public class AlliesFrontController {
	@Autowired
	private UserService userService;
	@Autowired
	private AnnouncementService annoService;
		
	@RequestMapping(value="/front/allies/announcement/list")
	public String showAlliesAnnouncement(Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize ){
		Page<Announcement> page = annoService.findAllByType(pageNumber, pageSize, "allies");
		model.addAttribute("page", page);
		return "front.allies.announcement.list";
	}
	
	@RequestMapping(value="/front/allies/announcement/view/{id}")
	public String showAlliesAnnouncementDetail(Model model,@PathVariable Long id ){
		Announcement anno = annoService.findOne(id);
		model.addAttribute("announcement", anno);
		return "front.allies.announcement.view";
	}
	
	@RequestMapping(value="/allies/{user_id}/announcement/list")
	public String showUserAlliesAnnouncement(Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize,@PathVariable Long user_id ){
		User user = userService.findOne(user_id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("userInfo", userInfo);	
		
		Page<Announcement> page = annoService.findAllAnnoByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return "allies.user.announcement.list";
	}
	
	@RequestMapping(value="/allies/{user_id}/announcement/view/{id}")
	public String showUserAlliesAnnouncementDetail(Model model,@PathVariable Long id,@PathVariable Long user_id ){
		User user = userService.findOne(user_id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("userInfo", userInfo);
		 
		Announcement anno = annoService.findOne(id);
		model.addAttribute("announcement", anno);
		return "allies.user.announcement.view"; 
	}
}
