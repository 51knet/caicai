package com.knet51.patents.controllers.admin.kefu;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.patents.jpa.services.patent.PatentService;

@Controller
public class KefuPageController {
	private static final Logger logger = LoggerFactory.getLogger(KefuPageController.class);
	@Autowired
	private PatentService patentService;
	
	@RequestMapping(value="/admin/kefu")
	public String showKefuAdmin(){
		
		return "redirect:/admin/kefu/patent/list";
	}
	
	@RequestMapping(value="/admin/kefu/patent/list")
	public String showAllPatent(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Patent> page = patentService.findPatent(pageNumber, pageSize);
		return "";
	}
	
}
