package com.knet51.ccweb.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
public class DebugController { 

	private static final Logger logger = LoggerFactory
			.getLogger(DebugController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;

	@Transactional
	@RequestMapping(value = "/debug", method = RequestMethod.GET)
	public String debug(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		List<User> userList = userService.findAllUsers();
		model.addAttribute("userList", userList);
		logger.info(userList.toString());
		
		return "debug";
	}
	
	
	@Transactional
	@RequestMapping(value = "/one2one/{user_id}/{student_collage}", method = RequestMethod.GET)
	public String one2one_ext(Locale locale, Model model, @PathVariable Long user_id, @PathVariable String student_collage) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		User user = userService.findOne(user_id);
		Student student = new Student(user);
		student.setId(user.getId());
		student.setUser(user);
		student.setCollege(student_collage);
		student.setRole(1);
		studentService.createStudent(student);
		
		logger.info(user.toString());
		logger.info(student.toString());

		Student st = studentService.findOne(user_id);
		logger.info(st.toString());
		model.addAttribute("user", user);
		model.addAttribute("student", st);
		
		return "db";
	}
	
	@Transactional
	@RequestMapping(value = "/student/{user_id}", method = RequestMethod.GET)
	public String getStudentByUserId(Locale locale, Model model, @PathVariable Long user_id) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		User user = userService.findOne(user_id);
		model.addAttribute("user", user);
		logger.info(user.toString());
		Student student = studentService.findStudentByUserId(user_id);
		logger.info(student.toString());
		
		model.addAttribute("student", student);
		
		return "db";
	}
}
