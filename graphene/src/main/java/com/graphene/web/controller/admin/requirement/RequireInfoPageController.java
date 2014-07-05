package com.graphene.web.controller.admin.requirement;

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

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.patent.PatentType;
import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;
import com.graphene.web.service.patent.PatentTypeService;
import com.graphene.web.service.requirement.TechRequireService;
import com.graphene.web.util.ajax.AjaxValidationEngine;
import com.graphene.web.util.ajax.ValidationResponse;

@Controller
public class RequireInfoPageController {
	@Autowired
	private TechRequireService requirementService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatentTypeService patentTypeService;

	
	@RequestMapping("/admin/requirement/list")
	public String showRequireList(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		Page<TechRequirement> page = requirementService.findRequireByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		
		return "admin."+user.getRole()+".requirement.list";
	}
	
	@RequestMapping("/admin/requirement/new")
	public String showCreatePage(HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<PatentType> pTypeList = patentTypeService.findAllPatentType();
		model.addAttribute("pTypeList", pTypeList);
		
		return "admin."+userInfo.getRole()+".requirement.new";
	}
	
	@RequestMapping("/admin/requirement/edit/{require_id}")
	public String editRequirement(HttpSession session,Model model,@PathVariable Long require_id){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		TechRequirement requirement = requirementService.findOne(require_id);
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
