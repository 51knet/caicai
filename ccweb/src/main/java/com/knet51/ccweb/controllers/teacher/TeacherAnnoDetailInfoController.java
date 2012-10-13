package com.knet51.ccweb.controllers.teacher;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

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
	@RequestMapping(value="/teacherDetailAnno", method = RequestMethod.POST)
	public String allAnnoInfo(@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm,
			BindingResult validResult, HttpSession session,Model m){
		logger.info("####  TeacherAnnoDetailController  ####");
		
		if(validResult.hasErrors()){
			logger.info("annoDetailInfoForm Validation Failed " + validResult);
			return "teacherAnnoPage";
		}else{
			logger.info("####  TeacherAnnoDetailController passed.  ####");
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			User user = userInfo.getUser();
			//System.out.println("++++++++++++++++"+user.getId()+"+++++++++++++++");
			String title = annoDetailInfoForm.getTitle();
			String content = annoDetailInfoForm.getContent();
			//System.out.println("------"+title+"-----"+content+"------");
			Announcement announcement = new Announcement();
			announcement.setTitle(title);
			announcement.setContent(content);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			announcement.setDate(date);  
			annoService.createAnnouncement(announcement, user);
			//System.out.println(announcement.getDate());
			List<Announcement> list = annoService.findAllByUid(user.getId());
			m.addAttribute("list", list);
			return "teacherHomePage";
		}
	}
	
	
	@RequestMapping(value="/teacherAnnoDetail")
	public String detailAnnoInfo(@RequestParam("id") Long id, Model m){
		//System.out.println(id);
		Announcement ann = annoService.findOneById(id);
		m.addAttribute("ann", ann);
		return "detailAnnInfo";
	}
	
	
	@RequestMapping(value="/teacherAnnoDele")
	public String teacherAnnoDele(@RequestParam("id") Long id,HttpSession session, Model m){
		annoService.deleAnnouncementById(id);
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		User user = userInfo.getUser();
		List<Announcement> list = annoService.findAllByUid(user.getId());
		m.addAttribute("list", list);
		return "teacherHomePage";
	}
	
	@Transactional
	@RequestMapping(value="/teacherAnnoUpdate" , method = RequestMethod.POST)
	public String teacherAnnoUpdate(@RequestParam("id") Long id,@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm,
			BindingResult validResult, HttpSession session,Model m){
		if(validResult.hasErrors()){
			Announcement ann = annoService.findOneById(id);
			m.addAttribute("ann", ann);
			return "detailAnnInfo";
		}else{	
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			User user = userInfo.getUser();
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
			List<Announcement> list = annoService.findAllByUid(user.getId());
			m.addAttribute("list", list);
			return "teacherHomePage";
		}
	}
}
