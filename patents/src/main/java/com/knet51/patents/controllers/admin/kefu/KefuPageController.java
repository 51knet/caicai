package com.knet51.patents.controllers.admin.kefu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.knet51.patents.jpa.services.patent.PatentService;

@Controller
public class KefuPageController {
	private static final Logger logger = LoggerFactory.getLogger(KefuPageController.class);
	@Autowired
	private PatentService patentService;
	
	@RequestMapping(value="/admin/kefu")
	public String showKefuAdmin(){
		
		return "redirect:/admin/kefu/patent/list/all";
	}
	
	
	
}
