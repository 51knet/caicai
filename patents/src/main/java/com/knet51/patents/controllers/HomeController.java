package com.knet51.patents.controllers;

import java.nio.charset.Charset;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;



@Controller
public class HomeController {
	

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	private UserService userService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session,
			HttpServletRequest request) {
		UserInfo sessionUserInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
//		List<User> teacherList = userService.findUserByRole("teacher");
//		List<User> userList = userService.findUserByRole("student");
//		List<User> enterpriseList = userService.findUserByRole("enterprise");
//		model.addAttribute("teacherCount", teacherList.size());
//		model.addAttribute("userCount", userList.size());
//		model.addAttribute("enterpriseCount", enterpriseList.size());
		Cookie[] cookies = request.getCookies();
		String email = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(GlobalDefs.COOKIE_IDENTITY)) {
					String val = cookie.getValue(); // the value in cookie was
													// encoded
					email = new String(Base64.decode(val.getBytes()),
							Charset.forName("US-ASCII"));
					logger.debug("cookie encodedEmail:" + val
							+ ";decodedEmail:" + email);
					break;
				}
			}

			if (email != null && !email.equals("")) {
				User user = userService.findByEmailAddress(email); 
				sessionUserInfo = new UserInfo(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO,
						sessionUserInfo);
				return "redirect:/admin";
			}
			
		}

		// we can achieve auto login through above code,
		// comment it out for now since I am not quite clear how we should
		// control the auto login

		if (sessionUserInfo != null) {
			return "redirect:/admin";
		} else {
			return "home";
		}
	}

}
