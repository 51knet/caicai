package com.knet51.ccweb.controllers.admin.cservice;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.patent.PatentService;

@Controller
public class PatentController {
	@Autowired
	private UserService userService;
	@Autowired
	private PatentService patentService;
	
	@RequestMapping(value="/admin/cservice/patent/list")
	public String showCservicePatent(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<Patent> page = patentService.findPatent(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.cservice.patent.list";
	}
	
	@RequestMapping(value="/admin/cservice/patent/view")
	public String showCservicePatentView(@RequestParam("id") String patentNum, Model model){
		Patent patent = patentService.findOne(patentNum);
		model.addAttribute("patent", patent);
		
		return "admin.cservice.patent.view";
	}
}
