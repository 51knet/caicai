package com.knet51.ccweb.controllers.teacher;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TeacherController {

	private static final Logger logger = LoggerFactory
			.getLogger(TeacherController.class);

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;

	@Transactional
	@RequestMapping(value = "/admin/teacher/details")
	public String detailInfo(@Valid TeacherDetailInfoForm detailInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### DetailInfoController ####");

		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "admin.teacher.details";
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			String role = detailInfoForm.getRole();
			String college = detailInfoForm.getCollege();
			String major = detailInfoForm.getMajor();
			
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			User user = userService.findOne(userInfo.getId());
			Teacher teacher = new Teacher(user);
			teacher.setRole(Integer.valueOf(role));
			teacher.setCollege(college);
			teacher.setMajor(major);
			teacher = teacherService.updateTeacher(teacher);
			userInfo.setTeacher(teacher);
			
			session.setAttribute("userInfo", userInfo);

			return "admin.teacher.details";
		}
	}
}
