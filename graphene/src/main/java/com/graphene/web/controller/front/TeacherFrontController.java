package com.graphene.web.controller.front;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.patent.Patent;
import com.graphene.web.jpa.entity.teacher.Teacher;
import com.graphene.web.jpa.entity.tech.Technology;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.TeacherService;
import com.graphene.web.service.UserService;
import com.graphene.web.service.announcement.AnnouncementService;
import com.graphene.web.service.patent.PatentService;
import com.graphene.web.service.tech.TechnologyService;

@Controller
public class TeacherFrontController {
	private static final Logger logger = LoggerFactory
			.getLogger(TeacherFrontController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private TechnologyService techService;

	@RequestMapping(value = "/teacher/{id}/patent/list")
	public String teacherPatent(@PathVariable Long id, Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize )
			throws IOException {
		
		logger.info("#### Into teacher patent page ####");
		try {
			User user = userService.findOne(id);
			if (user.getRole().equals("teacher")) {	
				Teacher teacher = teacherService.findOne(id);				
				Page<Patent> page = patentService.findPatentByUserAndStatus(pageNumber, pageSize, user, GlobalDefs.PASS);
				model.addAttribute("page", page);


				UserInfo userInfo = new UserInfo(user);
				userInfo.setTeacher(teacher);

				model.addAttribute("userInfo", userInfo);					
				return "teacher.patent.list";
			} else {
				return "redirect:/id/" + id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404"; 
		}
	}
	@RequestMapping(value = "/teacher/{id}/patent/view/{patent_id}")
	public String teacherPatentView(@PathVariable Long id,@PathVariable long patent_id, Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize )
			throws IOException {
		
		logger.info("#### Into teacher patent view page ####");
		try {
			User user = userService.findOne(id);
			if (user.getRole().equals("teacher")) {	
				Teacher teacher = teacherService.findOne(id);				
				Patent patent = patentService.findOne(patent_id);
				model.addAttribute("patent", patent);

				UserInfo userInfo = new UserInfo(user);
				userInfo.setTeacher(teacher);
 
				model.addAttribute("userInfo", userInfo);					
				return "teacher.patent.view";
			} else {
				return "redirect:/id/" + id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/teacher/{id}/technology/list")
	public String teacherTech(@PathVariable Long id, Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize )
			throws IOException {
		
		logger.info("#### Into teacher technology page ####");
		try {
			User user = userService.findOne(id);
			if (user.getRole().equals("teacher")) {	
				Teacher teacher = teacherService.findOne(id);				
				Page<Technology> page = techService.findAllByUserAndStatus(user, GlobalDefs.PASS, pageNumber, pageSize);
				model.addAttribute("page", page);


				UserInfo userInfo = new UserInfo(user);
				userInfo.setTeacher(teacher);

				model.addAttribute("userInfo", userInfo);					
				return "teacher.technology.list";
			} else {
				return "redirect:/id/" + id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		} 
	}
	@RequestMapping(value = "/teacher/{id}/technology/view/{tech_id}")
	public String teacherFront(@PathVariable Long id,@PathVariable long tech_id, Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize )
			throws IOException {
		
		logger.info("#### Into teacher technology view page ####");
		try {
			User user = userService.findOne(id);
			if (user.getRole().equals("teacher")) {	
				Teacher teacher = teacherService.findOne(id);				
				Technology tech = techService.findOne(tech_id);
				model.addAttribute("technology", tech);


				UserInfo userInfo = new UserInfo(user);
				userInfo.setTeacher(teacher);

				model.addAttribute("userInfo", userInfo);					
				return "teacher.technology.view";
			} else {
				return "redirect:/id/" + id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}
}
