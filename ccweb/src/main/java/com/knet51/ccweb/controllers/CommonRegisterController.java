package com.knet51.ccweb.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.MailSender;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommonRegisterController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonRegisterController.class);

	@Autowired
	private UserService userService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/CommonRegister", method = RequestMethod.GET)
	public String commonRegister(HttpServletRequest request,
			HttpServletResponse reponse) {
		logger.info("#### into commonRegisterController ####");

		String emailString = request.getParameter("email");
		String psw = request.getParameter("psw");
		
		User result = userService.findByEmailAddress(emailString);

		if (result == null) {
			logger.info("valiable email.");
			User usr = new User(emailString, psw);
			String randomUrl = MailSender.getInstance().produceRandomString();
			usr.setRandomUrl(randomUrl);
			result = userService.createUser(usr);
			randomUrl += result.getId();
			MailSender.getInstance().SendMail(emailString,
					"http://localhost:8080/ccweb/mail/"+randomUrl);
			return "ok";
		} else {
			logger.info(result.getPassword());
			return "bye";
		}
	}
}
