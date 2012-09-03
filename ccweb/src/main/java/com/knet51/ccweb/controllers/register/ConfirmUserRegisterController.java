package com.knet51.ccweb.controllers.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class ConfirmUserRegisterController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonRegisterController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/mail/{randomUrl}/{idString}", method = RequestMethod.GET)
	public String commonRegister(@PathVariable String randomUrl,
			@PathVariable String idString) {
		logger.info("#### into ConfirmUserRegisterController ####");
		Integer id = Integer.parseInt(idString);
		User result = userService.findOne(id.longValue());
		boolean userConfirmed = (result != null) && randomUrl.equals(result.getRandomUrl());
		if (userConfirmed) {
			logger.info("#### into result not null #### " + result.getName());
			// update the randomUrl to pass user confirm;
			result.setRandomUrl("pass");
			userService.updateUser(result);
			// return successfully login and give the detail register page;
			//TODO: get the login session;
			boolean succeed = userService.login(result.getEmail(), result.getPassword());
			logger.info("Confirm user email " + succeed);
			return "detailRegisterUserInfoPage";
		} else {
			
			logger.info("#### user confirm failed ####");
			
			return "home";
		}
	}
}
