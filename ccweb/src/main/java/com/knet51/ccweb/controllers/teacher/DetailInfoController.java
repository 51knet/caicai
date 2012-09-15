package com.knet51.ccweb.controllers.teacher;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.services.TeacherService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DetailInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DetailInfoController.class);

	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value = "/teacherDetailInfo", method = RequestMethod.POST)
	public String commonRegister(@Valid DetailInfoForm detailInfoForm,
			BindingResult validResult) {
		logger.info("#### DetailInfoController ####");

		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "teacherInfoPage";
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			String role = detailInfoForm.getRole();
			String college = detailInfoForm.getCollege();
			String major = detailInfoForm.getMajor();
			
			Teacher teacher = new Teacher();
			teacher.setRole(Integer.valueOf(role));
			teacher.setCollege(college);
			teacher.setMajor(major);
			
			teacherService.createTeacher(teacher);
			
			return "teacherInfoPage";
		}
	}
}
