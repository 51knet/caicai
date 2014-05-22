package com.knet51.patents.controllers.common.tech;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.technology.TechnologyService;

public class CommonTechController {
	@Autowired
	private UserService userService;
	@Autowired
	private TechnologyService techService;
	@RequestMapping(value="/{roletype}/{u_id}/technology/list")
	public String showTechnologyList(@PathVariable String roletype, @PathVariable Long u_id, HttpSession session, Model model,
			@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize ){
		User user = userService.findOne(u_id); 
		Page<Technology> page = techService.findAllByUserAndStatus(user, GlobalDefs.PASS, pageNumber, pageSize);
		model.addAttribute("page", page);
		return roletype+".technology.list";
	}
	
	@RequestMapping(value="/{roletype}/{u_id}/technology/view/t_id")
	public String showTechnologyView(@PathVariable String roletype, @PathVariable Long u_id,@PathVariable Long t_id,Model model){
		Technology technology = techService.findOne(t_id);
		model.addAttribute("technology", technology);
		return roletype+".technology.detail";
	}
}
