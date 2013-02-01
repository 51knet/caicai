package com.knet51.courses.controllers.signup;

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
import com.knet51.courses.util.mailSender.MailSender;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SignupController {

	private static final Logger logger = LoggerFactory
			.getLogger(SignupController.class);
	@Autowired
	private UserService service;

	@RequestMapping(value = "signup", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String commonRegister(@Valid SignupForm signupForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### signupController ####");

		if (validResult.hasErrors()) {
			logger.info("signupForm Validation Failed " + validResult);
			return "signup";
		} else {
			String email = signupForm.getEmail();
			String psw = signupForm.getPsw();
			User findUser = service.getValidEmail(email);
			if (findUser == null) {
				User user = new User(email, psw);
				// boolean mailSuccess = false;
				String randomUrl = MailSender.getInstance()
						.produceRandomString();
				user.setRandomUrl(randomUrl);
				user.setPhoto_url("/resources/img/avatar/avatar90.png");
				user = service.createUser(user);
				UserInfo userInfo = new UserInfo(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
				logger.info(userInfo.getEmail() + " = " + userInfo.getId());
				return "redirect:/";
				// return "signup.successful";
				// randomUrl += "/";
				// randomUrl += findUser.getId();
				// mailSuccess = MailSender.getInstance().SendMail(email,
				// "http://www.51knet.com/ccweb/mail/" + randomUrl);
				// if (mailSuccess) {
				// String hrefString = email;
				// hrefString = hrefString
				// .substring(hrefString.indexOf("@") + 1);
				// model.addAttribute("hrefString", hrefString);
				// return "signup.successful";
				// } else {
				// return "404";
				// }
			} else {
				return "redirect:/";
			}
		}
	}
}
