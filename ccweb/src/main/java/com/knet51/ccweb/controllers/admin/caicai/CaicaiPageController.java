package com.knet51.ccweb.controllers.admin.caicai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CaicaiPageController {
	private static final Logger logger = LoggerFactory
			.getLogger(CaicaiPageController.class);
	
	@RequestMapping(value="/admin/caicai")
	public String showSuperAdmin(){
		return "admin.caicai.detail";
	}
}
