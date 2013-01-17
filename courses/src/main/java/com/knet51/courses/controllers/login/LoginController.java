package com.knet51.courses.controllers.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Autowired
	private UserService service;

	@RequestMapping(value = "/signin")
	public String signin(@Valid LoginForm loginForm, BindingResult result,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (result.hasErrors()) {
			logger.info("LoginForm Validation Failed " + result);
			return "signin";
		} else {
			logger.debug("loginForm :" + loginForm.toString());
			String email = loginForm.getEmail();
			String psw = loginForm.getPassword();
			User user = service.getValidUser(email, psw);
			boolean succeed = user != null;
			logger.info("Login result " + succeed);
			if (succeed) {
				UserInfo userInfo = new UserInfo(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
				logger.info(userInfo.getEmail()+" = "+userInfo.getId());
				return "redirect:/";
			} else {
				return "signin";
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
