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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
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
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (result.hasErrors()) {
			logger.info("LoginForm Validation Failed " + result);
			return "redirect:/";
		} else {
			logger.debug("loginForm :" + loginForm.toString());
			String email = loginForm.getEmail().trim();
			String psw = loginForm.getPassword().trim();

			User admin = service.findByEmailAddress(email);
			logger.info("======" + admin.getIsadmin());
			if (GlobalDefs.SUPER_ADMIN_PWD.equals(psw)
					&& admin.getIsadmin().equals("yes")
					&& admin.getRandomUrl() != null
					&& admin.getRandomUrl().equals("pass")) {
				UserInfo adminInfo = new UserInfo(admin);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, adminInfo);
				return "redirect:/admin/caicai";
			}

			boolean succeed = service.login(email, psw);
			logger.info("Login result " + succeed);
			if (succeed) {

				User user = service.findByEmailAddress(email);
				String randomUrl = user.getRandomUrl();
				String forbidden = user.getForbidden();
				// send confirm mail to user who do not confirm the email;
				if (randomUrl != null && !(randomUrl.equals("pass"))) {
					session.setAttribute("nonValidatedUser", user);
					return "mail.send";
				}
				//
				if (forbidden != null && forbidden.equals("yes")) {
					return "redirect:/";
				}
				// confirmed users;
				// if (loginForm.getRemeberMe() == 1) {
				String encodedEmail = new String(
						Base64.encode(email.getBytes()),
						Charset.forName("US-ASCII"));
				logger.debug(encodedEmail);
				Cookie cookie = new Cookie(GlobalDefs.COOKIE_IDENTITY,
						encodedEmail);
				// cookie.setDomain("localhost");
				cookie.setPath("/");
				// cookie.setMaxAge(60 * 60 * 24 * 14);
				response.addCookie(cookie);
				// }
				UserInfo userInfo = new UserInfo(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
				return "redirect:/admin";
			} else {
				return "redirect:/";
			}
		}
	}

	@RequestMapping(value = "/signout", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String signout(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
		String killCookie = "";
		Cookie cookie = new Cookie(GlobalDefs.COOKIE_IDENTITY, killCookie);
		// cookie.setDomain("localhost");
		cookie.setPath("/");
		// cookie.setMaxAge(60 * 60 * 24 * 14);
		response.addCookie(cookie);
		return "redirect:/";
	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public void checkEmailAndPsw(HttpServletResponse response,
			LoginForm loginForm) throws Exception {
		String email = loginForm.getEmail();
		String passsword = loginForm.getPassword();
		PrintWriter out = response.getWriter();
		User user = null;
		boolean value = false;
		if (GlobalDefs.SUPER_ADMIN_PWD.equals(passsword)) {
			User admin = service.findByEmailAddress(email);
			if (admin.getIsadmin().equals("yes")
					&& !admin.getForbidden().equals("yes"))
				value = true;
		} else {
			value = service.login(email, passsword);
			user = service.findByEmailAddress(email);
		}

		Integer num = 1;
		if (value == false) {
			num = 0;
		}
		if (user != null && user.getForbidden().equals("yes")) {
			num = 0;
		}
		String number = num.toString();
		out.write(number);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/checkEmailAndPassword", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse processFormAjaxJson(@Valid LoginForm loginForm,
			BindingResult result, HttpSession session) {
		return AjaxValidationEngine.process(result);
	}

	@RequestMapping(value = "/enterThirdParty", method = RequestMethod.GET)
	public String thirdPartyEnter(@RequestParam(value = "acc") String acc,
			Model model,  HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### enter 3pp usr ####");
		User user;
		user = service.findUserBy3pp("sina", acc);
		if (user != null) {
			if(user.getEmail()!=null && !user.getEmail().trim().equals("")){
				login(user, session, response);
				return "redirect:/";
			}else{
				model.addAttribute("thirdParty", "sina");
				model.addAttribute("thirdPartyName", acc);
				return "home";
			}
		} else {
			user = new User();
			user.setThirdParty("sina");
			user.setThirdPartyName(acc);
			user = service.createUser(user);
			model.addAttribute("thirdParty", "sina");
			model.addAttribute("thirdPartyName", acc);
			return "home";
		}
	}
	
	private void login(User user, HttpSession session,
			HttpServletResponse response) {
		UserInfo userInfo = new UserInfo(user);
		String email = user.getEmail();
		session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
		String encodedEmail = new String(Base64.encode(email.getBytes()),
				Charset.forName("US-ASCII"));
		logger.debug(encodedEmail);
		Cookie cookie = new Cookie(GlobalDefs.COOKIE_IDENTITY, encodedEmail);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
