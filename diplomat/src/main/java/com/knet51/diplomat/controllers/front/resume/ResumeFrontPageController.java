package com.knet51.diplomat.controllers.front.resume;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WorkExp;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.diplomat.beans.UserInfo;
import com.knet51.diplomat.jpa.services.TeacherService;
import com.knet51.diplomat.jpa.services.UserService;
import com.knet51.diplomat.jpa.services.achievement.TeacherHonorService;
import com.knet51.diplomat.jpa.services.achievement.TeacherPatentService;
import com.knet51.diplomat.jpa.services.achievement.TeacherProjectService;
import com.knet51.diplomat.jpa.services.achievement.TeacherThesisService;
import com.knet51.diplomat.jpa.services.resume.EduBackgroundService;
import com.knet51.diplomat.jpa.services.resume.WorkExpService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ResumeFrontPageController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private WorkExpService workExpService;
	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private TeacherProjectService projectService;
	@Autowired
	private TeacherPatentService patentService;
	@Autowired
	private TeacherHonorService honorService;

	// teacher front page
	@RequestMapping(value = "/teacher/{teacher_id}/resume", method = RequestMethod.GET)
	public String list(@PathVariable Long teacher_id, Model model) {
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		List<EduBackground> eduInfo = eduBackgroundService
				.findEduListByTeacherId(teacher_id);
		if (eduInfo != null) {
			model.addAttribute("eduInfo", eduInfo);
			model.addAttribute("eduCount", eduInfo.size());
		}
		List<WorkExp> workInfo = workExpService.findWorkList(teacher_id);
		if (workInfo != null) {
			model.addAttribute("workInfo", workInfo);
			model.addAttribute("workCount", workInfo.size());
		}

		List<TeacherThesis> thesisList = thesisService
				.getAllThesisById(teacher_id);
		model.addAttribute("thesisList", thesisList);
		model.addAttribute("thesisCount", thesisList.size());

		List<TeacherProject> projectList = projectService
				.getAllProjectById(teacher_id);
		model.addAttribute("projectList", projectList);
		model.addAttribute("projectCount", projectList.size());

		List<TeacherPatent> patentList = patentService
				.getAllPatentById(teacher_id);
		model.addAttribute("patentList", patentList);
		model.addAttribute("patentCount", patentList.size());

		List<TeacherHonor> honorList = honorService.getAllHonorById(teacher_id);
		model.addAttribute("honorList", honorList);
		model.addAttribute("honorCount", honorList.size());
		return "teacher.resume";
	}
}
