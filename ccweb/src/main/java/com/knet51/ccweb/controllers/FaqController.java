package com.knet51.ccweb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaqController {
	private static final Logger logger = LoggerFactory
			.getLogger(FaqController.class);
	
	@RequestMapping(value="/about")
	public String showAboutUsInfo(){
		logger.info("====== into AboutUs page controller =====");
		return "faq.about";
	}
	@RequestMapping(value="/contact")
	public String showContactInfo(){
		logger.info("====== into contact page controller =====");
		return "faq.contact";
	}
	@RequestMapping(value="/legal")
	public String showLegalInfo(){
		logger.info("====== into Legal page controller =====");
		return "faq.legal";
	}
	@RequestMapping(value="/help")
	public String showHelpInfo(){
		logger.info("====== into Help page controller =====");
		return "faq.help";
	}
}
