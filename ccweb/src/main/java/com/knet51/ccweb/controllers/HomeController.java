package com.knet51.ccweb.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	private UserService service;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		Cookie[] cookies = request.getCookies();     // request is an instance of type 
        //HttpServletRequest
	boolean foundCookie = false;
	
	for(int i = 0; i < cookies.length; i++)
	{ 
		Cookie c = cookies[i];
		logger.info(c.toString());
		if (c.getName().equals("userInfo"))
		{
			String userId= c.getValue();
			logger.info("userId found in cookie"+userId);
			foundCookie = true;
		}
	}  
	
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
		User test = service.createUser(new User("test@test.com", "testuser", 1, 10));
		logger.info(test.toString());

		User u = service.findOne(Long.valueOf("1"));
		model.addAttribute("user", u);
		return "db";
	}
}
