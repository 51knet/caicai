package com.knet51.diplomat.controllers.front.incubator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.incubator.IncubatInfor;
import com.knet51.diplomat.beans.UserInfo;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.UserService;
import com.knet51.diplomat.jpa.services.announcement.AnnouncementService;
import com.knet51.diplomat.jpa.services.incubator.IncubatInforService;

@Controller
@RequestMapping(value="/incubator/{id}")
public class IncubatorFrontPageController {
	@Autowired
	private UserService userService;
	@Autowired
	private IncubatInforService incubatService;
	@Autowired
	private AnnouncementService annoService;
	@RequestMapping(value="")
	public String showIncubatorHomePage(@PathVariable Long id,Model model){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("incubatUserInfo", userInfo);
		
		Page<Announcement> announcement = annoService.findAnnoByUserAndCode(0, 5, user, GlobalDefs.incubator);
		model.addAttribute("anno", announcement);
		IncubatInfor incubator = incubatService.findOne(id);
		model.addAttribute("incubator", incubator);
		return "incubator.basic";
	}
	
	@RequestMapping(value="/announcement/list")
	public String showAnnouncementList(@PathVariable Long id,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize,Model model){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("incubatUserInfo", userInfo);
		
		Page<Announcement> page = annoService.findAnnoByUserAndCode(pageNumber, pageSize, user, GlobalDefs.incubator);
		model.addAttribute("page", page);
		return "incubator.announcement.list";
	}
	
	@RequestMapping(value="/announcement/view/{anno_id}")
	public String showAnnouncementDetail(@PathVariable Long id,@PathVariable Long anno_id,Model model){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("incubatUserInfo", userInfo);
		
		
		Announcement anno = annoService.findOneById(anno_id);
		model.addAttribute("announcement", anno);
		return "incubator.announcement.view";
	}
	
	@RequestMapping(value="/about")
	public String showIncubatorInfor(Model model,@PathVariable Long id){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("incubatUserInfo", userInfo);
		
		IncubatInfor incubator = incubatService.findOne(id);
		model.addAttribute("incubator", incubator);
		return "incubator.about";
	}
	

}
