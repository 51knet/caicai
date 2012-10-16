package com.knet51.ccweb.controllers.register;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.mailSender.MailSender;

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
	@RequestMapping(value = "/register/common", method = RequestMethod.POST)
	public String commonRegister(@Valid CommonRegisterForm commonRegisterForm,
			BindingResult validResult) {
		logger.info("#### commonRegisterController ####");

		if (validResult.hasErrors()) {
			logger.info("commonRegisterForm Validation Failed " + validResult);
			return "register";
		} else {
			String email = commonRegisterForm.getEmail();
			String psw = commonRegisterForm.getPsw();
			User findUser = userService.findByEmailAddress(email);
			if (findUser == null) {
				User user = new User(email, psw);
				String randomUrl = MailSender.getInstance()
						.produceRandomString();
				user.setRandomUrl(randomUrl);
				findUser = userService.createUser(user);
				randomUrl += "/";
				randomUrl += findUser.getId();
				MailSender.getInstance().SendMail(email,
						"http://localhost:8080/ccweb/mail/" + randomUrl);
				return "register.successful";
			} else {
				return "register";
			}
		}
	}
}
