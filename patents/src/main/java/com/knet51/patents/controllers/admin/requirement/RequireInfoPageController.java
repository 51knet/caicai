package com.knet51.patents.controllers.admin.requirement;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.requirement.RequirTypeService;
import com.knet51.patents.jpa.services.requirement.RequirementService;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;

import com.knet51.ccweb.jpa.entities.RequirType;
import com.knet51.ccweb.jpa.entities.Requirement;
import com.knet51.ccweb.jpa.entities.User;


@Controller
public class RequireInfoPageController {
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private RequirTypeService requirTypeService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/admin/requirement/list")
	public String showRequireList(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		Page<Requirement> page = requirementService.findRequireByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return "admin."+user.getRole()+".requirement.list";
	}
	
	@RequestMapping("/admin/requirement/new")
	public String showCreatePage(HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		List<RequirType> requirTypeList = requirTypeService.findTypeList();
		model.addAttribute("requirTypeList", requirTypeList);
		
		return "admin."+user.getRole()+".requirement.new";
	}
	
	@RequestMapping("/admin/requirement/edit/{require_id}")
	public String editRequirement(HttpSession session,Model model,@PathVariable Long require_id){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		Requirement requirement = requirementService.findOne(require_id);
		List<RequirType> requirTypeList = requirTypeService.findTypeList();
		model.addAttribute("requirTypeList", requirTypeList);
		if(!requirement.getUser().getId().equals(user.getId())){
			return "redirect:/admin/requirement/list";
		}
		model.addAttribute("requirement", requirement);
		return "admin."+user.getRole()+".requirement.edit";
	}
	
	@RequestMapping(value = "/admin/requirement/new/createRequirementAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse requirementFormAjaxJson(@Valid RequireForm requireForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/requirement/edit/updateRequirementAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse requirementFormEditAjaxJson(@Valid RequireForm requireForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}

}
