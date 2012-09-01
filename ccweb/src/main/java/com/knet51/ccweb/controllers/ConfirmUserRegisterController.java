package com.knet51.ccweb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.dao.UserDao;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class ConfirmUserRegisterController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonRegisterController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/mail/{randomUrl}",  method = RequestMethod.GET)
	public String commonRegister(@PathVariable String randomUrl) {
		logger.info("#### into ConfirmUserRegisterController ####");
		
		User result = userService.findByRandomUrl(randomUrl);
		if(result != null){
			logger.info("#### into result not null #### "+result.getName());
			return "ok";
		}else{
			logger.info("#### into result null ####");
			return "bye";
		}
		
		
	}
}
