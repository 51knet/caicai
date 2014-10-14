package com.knet51.diplomat.controllers.admin.technology;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.diplomat.beans.UserInfo;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.UserService;
import com.knet51.diplomat.jpa.services.technology.TechnologyService;
import com.knet51.diplomat.util.ajax.AjaxValidationEngine;
import com.knet51.diplomat.util.ajax.ValidationResponse;

@Controller
public class TechnologyPageController {
	private static final Logger logger = LoggerFactory.getLogger(TechnologyPageController.class);
	@Autowired
	private TechnologyService technologyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/technology/list" ,method = RequestMethod.GET)
	public String showTechnologyList(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("===== into technology list controller ====");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		try {
			List<Technology> techList = technologyService.findListByUser(user);
			Page<Technology> page = technologyService.findAllByUser(user, pageNumber, pageSize);
			model.addAttribute("techCount", techList.size());
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin."+userInfo.getRole()+".technology.list";
	}
	
	@RequestMapping(value="/admin/technology/new",method = RequestMethod.GET)
	public String showTechnologyAddPage(HttpSession session, Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Map<String, String> techField = GlobalDefs.getTechField();
		model.addAttribute("techField", techField);
		return "admin."+userInfo.getRole()+".technology.new";
	}
	
	@RequestMapping(value="/admin/technology/edit/{tech_id}",method = RequestMethod.GET)
	public String technologyUpdatePage(@PathVariable Long tech_id,HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Map<String, String> techField = GlobalDefs.getTechField();
		model.addAttribute("techField", techField);
		Technology technology = technologyService.findOne(tech_id);
		if(!userInfo.getId().equals(technology.getUser().getId())){
			return "redirect:/admin";
		}
		model.addAttribute("technology", technology);
		return "admin."+userInfo.getRole()+".technology.edit";
	}
	
	@RequestMapping(value="/admin/technology/view/{tech_id}",method = RequestMethod.GET)
	public String technologyView(@PathVariable Long tech_id,HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Technology technology = technologyService.findOne(tech_id);
		if(!userInfo.getId().equals(technology.getUser().getId())){
			return "redirect:/admin";
		}
		model.addAttribute("technology", technology);
		return "admin."+userInfo.getRole()+".technology.view";
	}
	
	@RequestMapping(value = "/admin/technology/technologyInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormAjaxJson(@Valid TechnologyForm technologyForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/technology/edit/new/technologyInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormUpdateAjaxJson(@Valid TechnologyForm technologyForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
}
