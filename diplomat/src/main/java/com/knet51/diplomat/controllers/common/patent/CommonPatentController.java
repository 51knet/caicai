package com.knet51.diplomat.controllers.common.patent;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.UserService;
import com.knet51.diplomat.jpa.services.patent.PatentService;


public class CommonPatentController {
	@Autowired
	private PatentService patentService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{roletype}/{u_id}/patent/list")
	public String showPatentList(@PathVariable String roletype, @PathVariable Long u_id, HttpSession session, Model model,
			@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize ){
		User user = userService.findOne(u_id); 
		Page<Patent> page = patentService.findPatentByUserAndStatus(pageNumber, pageSize, user, GlobalDefs.PASS);
		model.addAttribute("page", page);
		return roletype+".patent.list";
	}
	
	@RequestMapping(value="/{roletype}/{u_id}/patent/view")
	public String showPatentView(@PathVariable String roletype, @PathVariable Long u_id,
			@RequestParam("patentNum") String patentNum,Model model){
		Patent patent = patentService.findOne(patentNum);
		model.addAttribute("patent", patent);
		return roletype+".patent.detail";
	}
}
