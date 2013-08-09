package com.knet51.ccweb.controllers.admin.teacher.announcement;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.MyUtil;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class TeacherAnnoInfoPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherAnnoInfoPageController.class); 
	@Autowired
	private AnnouncementService annoService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/admin/announcement/list")
	public String teacherAnno(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
								int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		logger.info("#### into TeacherAnno ####");
			Long id = getUserId(session);
			User user = userService.findOne(id);
			if(user.getRole().equals("user")){
				return "redirect:/admin";
			}else{
				List<Announcement> list = annoService.findAllByUid(id);
				int pageNum = MyUtil.getPageNumber(pageNumber, list.size(),pageSize);
				Page<Announcement> page = annoService.findAllAnnoByUser(pageNum, pageSize, user);
				model.addAttribute("page", page);
				if (user.getRole().equals("teacher")) {
					return "admin.teacher.announement.list";
				} else if (user.getRole().equals("enterprise")) {
					return "admin.enterprise.announcement.list";
				} else {
					return "404";
				}
				//return "admin.announcement.list";
			}
	}
	
	@RequestMapping(value="/admin/announcement/edit/{anno_id}")
	public String detailAnnoInfo(HttpSession session, Model model,@PathVariable Long anno_id){
		Long currentuser_id = getUserId(session);
		Announcement announcement = annoService.findOneById(anno_id);
		Long user_id = announcement.getUser().getId();
		if(!currentuser_id.equals(user_id)||announcement==null){
			return "redirect:/admin";
		}else{
			model.addAttribute("anno", announcement);
			if (announcement.getUser().getRole().equals("teacher")) {
				return "admin.teacher.announement.edit";
			} else if (announcement.getUser().getRole().equals("enterprise")) {
				return "admin.enterprise.announcement.edit";
			} else {
				return "404";
			}
		}
	}
	
	@RequestMapping(value="/admin/announcement/create")
	public String detailAnno(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo.getUser().getRole().equals("teacher")) {
			return "admin.teacher.announement.new";
		} else if (userInfo.getUser().getRole().equals("enterprise")) {
			return "admin.enterprise.announcement.new";
		} else {
			return "404";
		}
	}
	@RequestMapping(value = "/admin/announcement/createAnnouncementAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse announcementInfoFormAjaxJson(@Valid TeacherAnnoDetailInfoForm 
			teacherAnnoDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	
	public Long getUserId(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long id = userInfo.getUser().getId();
		return id;
	}
	
}
