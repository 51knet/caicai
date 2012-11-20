package com.knet51.ccweb.controllers.teacher.achievement;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherThesisService;

@Controller
public class TeacherAchievePageController {
	private static final Logger logger = LoggerFactory.getLogger(TeacherAchievePageController.class);
	@Autowired
	private TeacherThesisService thesisService;
	
	@Autowired
	private TeacherProjectService projectService;
	
	@Autowired
	private TeacherPatentService patentService;
	
	@Autowired
	private TeacherHonorService honorService;
	
	@RequestMapping(value="/admin/teacher/achievement/list")
	public String teacherAchievement(HttpSession session,Model model){
		logger.info("#### Into teacher achievement page ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		List<TeacherThesis> thesis = thesisService.getAllThesisById(user.getId());
		List<TeacherProject> project = projectService.getAllProjectById(user.getId());
		List<TeacherPatent> patent = patentService.getAllPatentById(user.getId());
		List<TeacherHonor> honor = honorService.getAllHonorById(user.getId());
		model.addAttribute("project", project);
		model.addAttribute("projectCount", project.size());
		model.addAttribute("thesis", thesis);
		model.addAttribute("thesisCount", thesis.size());
		model.addAttribute("patent", patent);
		model.addAttribute("patentCount", patent.size());
		model.addAttribute("honor", honor);
		model.addAttribute("honorCount", honor.size());
		return "admin.teacher.achievement.list";
	}
	
	/* teacher front page */
	
	@RequestMapping(value="/teacher/{teacher_id}/thesis/list")
	public String showAllthesis(@PathVariable Long teacher_id,HttpSession session){
		return "";
	}
}
