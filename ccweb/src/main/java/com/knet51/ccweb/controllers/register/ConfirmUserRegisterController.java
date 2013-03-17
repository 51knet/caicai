package com.knet51.ccweb.controllers.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.mailSender.MailSender;

@Controller
public class ConfirmUserRegisterController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonRegisterController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/mail/{randomUrl}/{idString}", method = RequestMethod.GET)
	public String commonRegister(@PathVariable String randomUrl,
			@PathVariable String idString, HttpSession session) {
		logger.info("#### into ConfirmUserRegisterController ####");
		Integer id = Integer.parseInt(idString);
		User result = userService.findOne(id.longValue());
		boolean userConfirmed = (result != null)
				&& randomUrl.equals(result.getRandomUrl());
		if (userConfirmed) {

			logger.info("#### into result not null #### " + result.getName());
			result.setRandomUrl("pass");
			userService.updateUser(result);

			UserInfo userInfo = new UserInfo(result);

			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			logger.info("Confirm user email successful.");
			String type = result.getRole();
			if (type != null && type.equals("enterprise")) {
				return "redirect:/enterprise/dispatcher";
			} else if (type != null && type.equals("user")) {
				return "redirect:/user/dispatcher";
			} else {
				return "redirect:/teacher/dispatcher";
			}
		} else {
			logger.info("#### user confirm failed ####");
			return "home";
		}
	}

	@RequestMapping(value = "/sendMail", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String sendConfirmMail(HttpSession session,
			HttpServletRequest request, Model model) {
		User user = (User) session.getAttribute("nonValidatedUser");
		if (user != null) {
			boolean mailSuccess = false;
			String email = user.getEmail();
			String randomUrl = user.getRandomUrl();
			randomUrl += "/";
			randomUrl += user.getId();
			mailSuccess = MailSender.getInstance().SendMail(email,
					"http://www.51knet.com/ccweb/mail/" + randomUrl);
			if (mailSuccess) {
				String hrefString = email;
				hrefString = hrefString.substring(hrefString.indexOf("@") + 1);
				model.addAttribute("hrefString", hrefString);
				return "register.successful";
			} else {
				return "404";
			}
		} else {
			return "404";
		}

	}
}
