package com.knet51.ccweb.controllers.login;

import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.register.CommonRegisterForm;
import com.knet51.ccweb.controllers.teacher.TeacherPersonalInfoForm;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

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
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (result.hasErrors()) {
			logger.info("LoginForm Validation Failed " + result);
			return "home";
		} else {
			logger.debug("loginForm :" + loginForm.toString());
			String email = loginForm.getEmail();
			String psw = loginForm.getPassword();

			boolean succeed = service.login(email, psw);
			logger.info("Login result " + succeed);
			if (succeed) {
				if (loginForm.getRemeberMe() == 1) {
					String encodedEmail = new String(Base64.encode(email.getBytes()), Charset.forName("US-ASCII"));
					logger.debug(encodedEmail);
					Cookie cookie = new Cookie(GlobalDefs.COOKIE_IDENTITY, encodedEmail);
					cookie.setMaxAge(60*60*24*14);//remeber me for 2 weeks by default
					response.addCookie(cookie);
				}
				
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
	@RequestMapping(value="/checkLogin", method = RequestMethod.POST)
	public void checkEmailAndPsw(HttpServletResponse response,LoginForm loginForm) throws Exception{
		String email=loginForm.getEmail();
		String passsword=loginForm.getPassword();
		PrintWriter out=response.getWriter();
		boolean value=service.login(email, passsword);
		Integer num=1;
		if(value==false){
			num=0;
		}
		String number=num.toString();
		out.write(number);
		out.flush();
		out.close();
	}
	@RequestMapping(value = "/checkEmailAndPassword", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse processFormAjaxJson(@Valid LoginForm loginForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
}
