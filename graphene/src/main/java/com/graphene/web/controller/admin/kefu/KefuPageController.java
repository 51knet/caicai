package com.graphene.web.controller.admin.kefu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KefuPageController {
	private static final Logger logger = LoggerFactory.getLogger(KefuPageController.class);

	
	@RequestMapping(value="/admin/kefu")
	public String showKefuAdmin(){
		logger.info("-----------into admin kefu page controller");
		return "redirect:/admin/kefu/patent/list/all";
	}
	

	
}
