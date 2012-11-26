package com.knet51.ccweb.controllers.teacher.resume;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WorkExp;
import com.knet51.ccweb.jpa.services.EduBackgroundService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WorkExpService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TeacherResumeController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private WorkExpService workExpService;

	@Transactional
	@RequestMapping(value = "/admin/teacher/resume")
	public String resumePage(@RequestParam("active") String active,
			Model model, HttpSession session) {
		String universityFilePath = "";
		List<String> universityList = new ArrayList<String>();
		BufferedReader br;
		universityFilePath = session.getServletContext().getRealPath("/");
		universityFilePath += "resources\\university\\universities.property";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					universityFilePath), "utf-8"));
			String data = "";
			while ((data = br.readLine()) != null) {
				universityList.add(data);
			}
			br.close();
		} catch (FileNotFoundException e) {
			universityList.add(" ");
		} catch (IOException e) {
			universityList.add(" ");
		}
		if (active == null || active.equals("")) {
			active = "personal";
		}
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<EduBackground> eduInfo = eduBackgroundService
				.findEduInfoList(userInfo.getId());
		if (eduInfo != null) {
			model.addAttribute("eduInfo", eduInfo);
			model.addAttribute("eduCount", eduInfo.size());
		}
		List<WorkExp> workInfo = workExpService.findWorkList(userInfo.getId());
		if (workInfo != null) {
			model.addAttribute("workInfo", workInfo);
			model.addAttribute("workCount", workInfo.size());
		}
		model.addAttribute("active", active);
		model.addAttribute("universityList", universityList);
		return "admin.teacher.resume";
	}

	@RequestMapping(value = "/teacher/{teacher_id}/resume", method = RequestMethod.GET)
	public String list(@PathVariable Long teacher_id, Model model) {
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		List<EduBackground> eduInfo = eduBackgroundService
				.findEduInfoList(teacher_id);
		if (eduInfo != null) {
			model.addAttribute("eduInfo", eduInfo);
			model.addAttribute("eduCount", eduInfo.size());
		}
		List<WorkExp> workInfo = workExpService.findWorkList(teacher_id);
		if (workInfo != null) {
			model.addAttribute("workInfo", workInfo);
			model.addAttribute("workCount", workInfo.size());
		}
		return "teacher.resume";
	}
}
