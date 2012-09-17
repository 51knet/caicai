package com.knet51.ccweb.controllers.student;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.StudentService;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class StudentDetailInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentDetailInfoController.class);

	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;

	@Transactional
	@RequestMapping(value = "/studentDetailInfo", method = RequestMethod.POST)
	public String detailInfo(@Valid StudentDetailInfoForm detailInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### DetailInfoController ####");

		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "studentInfoPage";
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			String role = detailInfoForm.getRole();
			String college = detailInfoForm.getCollege();
			
			User user = (User) session.getAttribute("user");
			user = userService.findOne(user.getId());
			Student student = new Student(user);
			student.setId(user.getId());
			student.setRole(Integer.valueOf(role));
			student.setCollege(college);
			
			student = studentService.updateStudent(student);
			session.setAttribute("user", student);

			return "studentInfoPage";
		}
	}
}
