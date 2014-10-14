package com.knet51.diplomat.controllers.admin.incubator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.incubator.IncubatInfor;
import com.knet51.diplomat.beans.UserInfo;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.UserService;
import com.knet51.diplomat.jpa.services.incubator.IncubatInforService;
import com.knet51.diplomat.util.MyUtil;

@Controller
@RequestMapping(value="/admin/incubator")
public class IncubatorController {
	@Autowired
	private UserService userService;
	@Autowired
	private IncubatInforService incubatService;
	
	@RequestMapping(value="/about")
	public String showIncubatorInfor(HttpSession session, Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		IncubatInfor incubator = incubatService.findOne(userInfo.getId());
		model.addAttribute("incubator", incubator);
		return "admin.incubator.about";
	}
	
	@RequestMapping(value="/about/new", method=RequestMethod.POST)
	public String createIncubatorInfor(@Valid IncubatorForm incubatorForm,BindingResult result,
			HttpSession session, Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		IncubatInfor incubator = incubatService.findOne(userInfo.getId());
		if(incubator == null){
			incubator = new IncubatInfor(userInfo.getUser());
			MyUtil.copyValidBeanToDestBean(incubatorForm, incubator);
			incubatService.create(incubator);
		}else{
			MyUtil.copyValidBeanToDestBean(incubatorForm, incubator);
			incubatService.update(incubator);
		}
		return "redirect:/admin/incubator/about";
	}
}
