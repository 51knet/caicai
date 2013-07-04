package com.knet51.ccweb.controllers.common.friendsRelated;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class FriendsRelatedPageController {
	private Logger logger = LoggerFactory.getLogger(FriendsRelatedPageController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private FriendsRelateService relateService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/admin/friendsRelated/list")
	public String friendsRelatedDetail(HttpSession session, Model model){
		logger.info("#### Into FriendsRelatedPageController ####");
		Long id = getId(session);
		//System.out.println(id);
		int count = relateService.getAllFans(id).size();
		model.addAttribute("count", count);
		return "admin.teacher.friendsRelated.list";
	}
	@RequestMapping(value="/admin/friendsRelated/add")
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
	
	/**
	 * show sessionUser's fans list
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/fans/list")
	public String showFansList(HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<User> fansInfoList = relateService.getAllFansInfo(userInfo.getId());
		model.addAttribute("fansList", fansInfoList);
		
		
		return "admin."+userInfo.getRole()+".fans.list";
	}
	
	@RequestMapping(value="/admin/host/list")
	public String showHostList(HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<User> hostsInfoList = relateService.getAllHostInfo(userInfo.getId());
		model.addAttribute("hostList", hostsInfoList);
		
		
		return "admin."+userInfo.getRole()+".host.list";
	}
	
	
	
	/* teacher front page */
	
	// find the fans
	@RequestMapping(value="/teacher/{teacher_id}/fans/list")
	public String findFans(@PathVariable Long teacher_id, Model model,HttpSession  session){
		User user = userService.findOne(teacher_id);
	
		Teacher teacher = teacherService.findOne(teacher_id);
		
		List<User> fansInfoList = relateService.getAllFansInfo(teacher_id);
		
//		int followValue=relateService.getFollowById(teacher_id,userInf.getId());
		
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		
//		Integer fansCount = relateService.getAllFans(teacher_id).size();
//		Integer hostCount =  relateService.getAllHost(teacher_id).size();
		
		model.addAttribute("teacher_id", teacher_id);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("role", userInfo.getTeacherRole());
//		model.addAttribute("followValue",followValue);
//		model.addAttribute("fansCount", fansCount);
//		model.addAttribute("hostCount", hostCount);
		model.addAttribute("fansList", fansInfoList);
		return "teacher.fans.list";
	}
	
	// find the host
	
	@RequestMapping(value="/teacher/{teacher_id}/host/list")
	public String findHost(@PathVariable Long teacher_id, Model model,HttpSession  session){
		User user = userService.findOne(teacher_id);
		logger.info("###### into teacher find host controller ###########");
		Teacher teacher = teacherService.findOne(teacher_id);
		
		List<User> hostInfoList = relateService.getAllHostInfo(teacher_id);
		
//		int followValue=relateService.getFollowById(teacher_id,userInf.getId());
		
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		
//		Integer fansCount = relateService.getAllFans(teacher_id).size();
//		Integer hostCount =  relateService.getAllHost(teacher_id).size();
		
		model.addAttribute("teacher_id", teacher_id);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("role", userInfo.getTeacherRole());
//		model.addAttribute("followValue",followValue);
//		model.addAttribute("fansCount", fansCount);
//		model.addAttribute("hostCount", hostCount);
		model.addAttribute("hostList", hostInfoList);
		return "teacher.host.list";
	}
}
