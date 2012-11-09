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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class TeacherAnnoDetailInfoController {
	
	private static Logger logger = 
			LoggerFactory.getLogger(TeacherAnnoDetailInfoController.class);
	
	@Autowired
	private AnnouncementService annoService;
	@Autowired
	private UserService userService;
	
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/addAnnoInfo", method = RequestMethod.POST)
	public String allAnnoInfo(@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm,
			BindingResult validResult, HttpSession session,Model m){
		logger.info("####  TeacherAnnoDetailController  ####");
		
		if(validResult.hasErrors()){
			logger.info("annoDetailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/announcement/detail";
		}else{
			logger.info("####  TeacherAnnoDetailController passed.  ####");
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			User user = userInfo.getUser();
			//System.out.println("++++++++++++++++"+user.getId()+"+++++++++++++++");
			String title = annoDetailInfoForm.getTitle();
			String content = annoDetailInfoForm.getContent();
			Announcement announcement = new Announcement();
			announcement.setTitle(title);
			announcement.setContent(content);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			announcement.setDate(date);  
			annoService.createAnnouncement(announcement, user);
			//System.out.println(announcement.getDate());
			
			return "redirect:/admin/teacher/announcement/detail";
		}
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/deleAnno")
	public String teacherAnnoDele(@RequestParam("id") Long id,HttpSession session, Model m){
		annoService.deleAnnouncementById(id);
		return "redirect:/admin/teacher/announcement/detail";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/updateAnno" , method = RequestMethod.POST)
	public String teacherAnnoUpdate(@RequestParam("id") Long id,@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm,
			BindingResult validResult, HttpSession session,Model m){
		if(validResult.hasErrors()){
			return "/admin/teacher/announcement/detailOne";
		}else{	
			String title = annoDetailInfoForm.getTitle();
			String content = annoDetailInfoForm.getContent();
			//System.out.println("------"+title+"-----"+content+"------");
			Announcement announcement = annoService.findOneById(id);
			announcement.setTitle(title);
			announcement.setContent(content);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			announcement.setDate(date);  
			annoService.updateAnnouncement(announcement);
			return "redirect:/admin/teacher/announcement/detail";
		}
	}
}
