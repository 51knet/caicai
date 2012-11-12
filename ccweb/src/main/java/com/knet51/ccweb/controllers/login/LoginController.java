package com.knet51.ccweb.controllers.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Autowired
	private UserService service;
	@Autowired
	private AnnouncementService annoService;

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(@Valid LoginForm loginForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {
		if (result.hasErrors()) {
			logger.info("LoginForm Validation Failed " + result);
			return "home";
		} else {
			String email = loginForm.getEmail();
			String psw = loginForm.getPassword();

			boolean succeed = service.login(email, psw);
			logger.info("Login result " + succeed);
			if (succeed) {
				User user = service.findByEmailAddress(email);
				String randomUrl = user.getRandomUrl();
				// send confirm mail to user who do not confirm the email;
				if (randomUrl != null && !(randomUrl.equals("pass"))) {
					session.setAttribute("nonValidatedUser", user);
					return "mail.send";
				}
				// confirmed users;
				UserInfo userInfo = new UserInfo(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);

				return "redirect:/admin";
			} else {
				return "home";
			}
		}
	}

	@RequestMapping(value = "/signout", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String signout(HttpSession session, HttpServletRequest request) {
		session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
		return "redirect:/";
	}
}
