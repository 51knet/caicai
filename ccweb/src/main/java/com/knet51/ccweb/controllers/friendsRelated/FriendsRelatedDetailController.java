package com.knet51.ccweb.controllers.friendsRelated;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Friends_Related;
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
	public String askFriendsRelated(@RequestParam("uid") Long id,HttpSession session){
		logger.info("#### Into FriendsRelated addDetailPage ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		Friends_Related friendsReiated = new Friends_Related();
		friendsReiated.setHost_id(id);
		friendsReiated.setFollow_id(user.getId());
		friendsReiated.setType(1);
		friendsRelateService.save(friendsReiated);
		return "redirect:/teacher/" + id;
	}
}
