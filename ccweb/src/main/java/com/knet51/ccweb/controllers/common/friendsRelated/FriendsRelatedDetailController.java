package com.knet51.ccweb.controllers.common.friendsRelated;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.FriendsRelated;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class FriendsRelatedDetailController {	
	private Logger logger = 
			LoggerFactory.getLogger(FriendsRelatedDetailController.class);  
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendsRelateService friendsRelateService; 
	
	@RequestMapping(value="/addrelation")
	public String askFriendsRelated(@RequestParam("uid") Long host_id,HttpSession session){
		logger.info("#### Into FriendsRelated addDetailPage ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long user_id = userInfo.getId();
		//User user = userService.findOne(user_id);
		User host = userService.findOne(host_id);
		FriendsRelated friendsReiated = new FriendsRelated();
		friendsReiated.setHost_id(host_id);
		friendsReiated.setFollow_id(user_id);
		friendsReiated.setGroup(host.getRole());
		friendsRelateService.save(friendsReiated);
		return "redirect:/teacher/" + host_id;
	}
	
}
