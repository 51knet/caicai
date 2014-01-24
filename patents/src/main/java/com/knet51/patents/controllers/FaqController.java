package com.knet51.patents.controllers;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaqController {
	private static final Logger logger = LoggerFactory
			.getLogger(FaqController.class);
	
	@RequestMapping(value="/about")
	public void showAboutUs(HttpServletResponse response) throws Exception{
		response.sendRedirect("/courses/about");
		return ;
	}
	@RequestMapping(value="/contact")
	public void showContact(HttpServletResponse response) throws Exception{
		response.sendRedirect("/courses/contact");
		return ;
	}
	@RequestMapping(value="/legal")
	public void showLegal(HttpServletResponse response) throws Exception{
		response.sendRedirect("/courses/legal");
		return ;
	}
	@RequestMapping(value="/help")
	public void showHelp(HttpServletResponse response) throws Exception{
		response.sendRedirect("/courses/help");
		return ;
	}
}
