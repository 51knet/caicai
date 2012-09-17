package com.knet51.ccweb.controllers.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application register page.
 */
@Controller
public class StudentInfoPageController {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentInfoPageController.class);


	@RequestMapping(value = "/studentInfoPage")
	public String RegisterPage() {
		logger.info("#### into StudentInfoController ####");

			return "studentInfoPage";
	}
}
