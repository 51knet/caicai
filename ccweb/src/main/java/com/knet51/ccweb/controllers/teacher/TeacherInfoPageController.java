package com.knet51.ccweb.controllers.teacher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application register page.
 */
@Controller
public class TeacherInfoPageController {

	private static final Logger logger = LoggerFactory
			.getLogger(TeacherInfoPageController.class);


	@RequestMapping(value = "/teacherInfoPage")
	public String RegisterPage() {
		logger.info("#### into TeacherInfoController ####");

			return "teacherInfoPage";
	}
}
