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
import com.knet51.ccweb.controllers.admin.teacher.TeacherContactInfoForm;
import com.knet51.ccweb.controllers.admin.teacher.TeacherWorkExpInfoForm;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
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
								int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("#### into TeacherAnno ####");
			Long id = getId(session);
			User user = userService.findOne(id);
			if(user.getRole().equals("user")){
				return "redirect:/admin";
			}else{
				Page<Announcement> page = annoService.findAllAnnoByUser(pageNumber, pageSize, user);
				model.addAttribute("page", page);
				return "admin.announcement.list";
			}
	}
	
	@RequestMapping(value="/admin/announcement/edit/{anno_id}")
	public String detailAnnoInfo(HttpSession session, Model model,@PathVariable Long anno_id){
		Long currentuser_id = getId(session);
		Announcement announcement = annoService.findOneById(anno_id);
		Long user_id = announcement.getUser().getId();
		if(!currentuser_id.equals(user_id)||announcement==null){
			return "redirect:/admin/announcement/list";
		}else{
			model.addAttribute("anno", announcement);
			return "admin.announcement.edit";
		}
	}
	
	@RequestMapping(value="/admin/announcement/create")
	public String detailAnno(){
		
		return "admin.teacher.announcement.editKind";
	}
	@RequestMapping(value = "/admin/announcement/createAnnouncementAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse announcementInfoFormAjaxJson(@Valid TeacherAnnoDetailInfoForm 
			teacherAnnoDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	
	public Long getId(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long id = userInfo.getUser().getId();
		return id;
	}
	
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
