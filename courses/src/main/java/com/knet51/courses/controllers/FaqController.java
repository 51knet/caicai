package com.knet51.courses.controllers;

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
		return "faq.about";
	}
	@RequestMapping(value="/contact")
	public String showContactInfo(){
		return "faq.contact";
	}
	@RequestMapping(value="/legal")
	public String showLegalInfo(){
		return "faq.legal";
	}
	@RequestMapping(value="/help")
	public String showHelpInfo(){
		return "faq.help";
	}
}
