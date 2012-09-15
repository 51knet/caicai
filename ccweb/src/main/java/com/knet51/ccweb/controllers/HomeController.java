package com.knet51.ccweb.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
public class HomeController { 

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! the client locale is " + locale.toString());
	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@Transactional
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String db(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		User test = userService.createUser(new User("test@test.com", "testuser", 1, 10));
		logger.info(test.toString());

		User u = userService.findOne(Long.valueOf("1"));
		model.addAttribute("user", u);
		return "db";
	}
	@Transactional
	@RequestMapping(value = "/debug", method = RequestMethod.GET)
	public String debug(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		
		return "debug";
	}
	@Transactional
	@RequestMapping(value = "/one2one", method = RequestMethod.GET)
	public String one2one(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		User user = userService.findOne(1l);
		Student student = new Student();
		student.setUser(user);
		student.setCollege("college");
		student.setEmail("student@test.com");
		student.setRole(1);
		studentService.createStudent(student);
		
		logger.info(user.toString());
		logger.info(student.toString());

		User u = userService.findOne(Long.valueOf("1"));
		model.addAttribute("user", u);
		
		Student st = studentService.findOne(1l);
		logger.info(st.toString());

		
		return "db";
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
		Student student = new Student();
		student.setId(user.getId());
		student.setUser(user);
		student.setCollege(student_collage);
		student.setEmail("student@"+student_collage+".edu");
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
