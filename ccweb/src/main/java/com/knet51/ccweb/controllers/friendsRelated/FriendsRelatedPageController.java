package com.knet51.ccweb.controllers.friendsRelated;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class FriendsRelatedPageController {
	private Logger logger = LoggerFactory.getLogger(FriendsRelatedPageController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private FriendsRelateService relateService;
	
	@RequestMapping(value="/admin/teacher/friendsRelated/list")
	public String friendsRelatedDetail(HttpSession session, Model model){
		logger.info("#### Into FriendsRelatedPageController ####");
		Long id = getId(session);
		//System.out.println(id);
		int count = relateService.getAllFollow(id).size();
		model.addAttribute("count", count);
		return "admin.teacher.friendsRelated.list";
	}
	@RequestMapping(value="/admin/teacher/friendsRelated/add")
	public String friendsRelatedAddPage(Model model,HttpServletRequest request,HttpSession session){
		logger.info("#### Into FriendsRelatedAdd ####");
		//Long id = getId(session);
		//List<Friends_Related> friendsReList =relateService.getAllHost(id);
		List<User> allUser = userService.findAllUsers();
		
//		List<User> allUser = new ArrayList<User>();
//		for(int i=0;i<friendsReList.size();i++){
//			User user = userService.findOne(friendsReList.get(i).getHost_id());
//			allUser
//		}
		request.setAttribute("allUser", allUser);
		return "admin.teacher.friendRelated.addPage";
	}
	
	public Long getId(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long id = userInfo.getId();
		return id;
	}

}
