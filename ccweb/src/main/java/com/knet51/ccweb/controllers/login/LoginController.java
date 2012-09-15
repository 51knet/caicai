package com.knet51.ccweb.controllers.login;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.CookieGenerator;

import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.mailSender.MailSender;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Autowired
	private UserService service;

//	/**
//	 * Simply selects the home view to render by returning its name.
//	 */
//	@RequestMapping(value = "/signin", method = RequestMethod.GET)
//	public String signin(Locale locale, Model model,
//			@ModelAttribute LoginForm loginForm, BindingResult result) {
//		logger.info("Welcome home! the client locale is " + locale.toString());
//
//		new LoginFormValidator().validate(loginForm, result);
//		if (result.hasErrors()) {
//			logger.info("LoginForm Validation Failed " + result);
//			return "home";
//		} else {
//			String email = loginForm.getEmail();
//			String psw = loginForm.getPassword();
//			boolean succeed = service.login(email, psw);
//			logger.info("Login result " + succeed);
//			if (succeed) {
//				boolean activate;
//				activate = service.activate(email);
//				if (activate) {
//					return "redirect:home";
//				} else {
//					MailSender.getInstance().SendConfirmMail(email, service);
//					return "registerSuccessful";
//				}
//			} else {
//				return "redirect:home";
//			}
//		}
//	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(@Valid LoginForm loginForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		if (result.hasErrors()) {
			logger.info("LoginForm Validation Failed " + result);
			return "home";
		} else {
			String email = loginForm.getEmail();
			String psw = loginForm.getPassword();
			boolean rememberMe = loginForm.getRemeberMe();
			
			boolean succeed = service.login(email, psw);
			logger.info("Login result " + succeed);
			if (succeed) {
//				if(rememberMe) {
//					CookieGenerator cg = new CookieGenerator();
//					cg.setCookieName("userInfo");
//					cg.setCookieMaxAge(14*24*3600);
//					cg.setCookiePath(request.getContextPath());
//					cg.addCookie(response, email+"#"+psw);
//				}
				return "redirect:";
				// not sure what the following logic for?
//				boolean activate;
//				activate = service.activate(email);
//				if (activate) {
//					return "redirect:home";
//				} else {
//					MailSender.getInstance().SendConfirmMail(email, service);
//					return "registerSuccessful";
//				}
			} else {
				return "redirect:home";
			}
		}
	}

}
