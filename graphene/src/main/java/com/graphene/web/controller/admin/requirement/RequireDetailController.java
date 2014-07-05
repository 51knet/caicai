package com.graphene.web.controller.admin.requirement;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;
import com.graphene.web.service.patent.PatentTypeService;
import com.graphene.web.service.requirement.TechRequireService;
import com.graphene.web.util.MyUtil;


@Controller
public class RequireDetailController {
	private static Logger logger = 
			LoggerFactory.getLogger(RequireDetailController.class);
	@Autowired
	private TechRequireService requirementService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PatentTypeService patentTypeService;
	
	@RequestMapping(value="/admin/requirement/add",method = RequestMethod.POST)
	public String createrequirement(@Valid RequireForm requireForm,BindingResult validResult,HttpSession session,
			Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/requirement/new";
		}else{
			TechRequirement requirement = new TechRequirement();
			MyUtil.copyValidBeanToDestBean(requireForm, requirement);
			requirement.setDate(new Date());
			requirement.setUser(user);
			requirement.setStatus(GlobalDefs.WAITE);
			
			requirementService.create(requirement);
			return "redirect:/admin/requirement/list";
		}
		
	}
	
	@RequestMapping(value="/admin/requirement/edit/edit",method = RequestMethod.POST)
	public String updateRequirement(@Valid RequireForm requireForm,BindingResult validResult,HttpSession session,
			@RequestParam("require_id") Long require_id,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/requirement/new";
		}else{
			
			TechRequirement requirement = new TechRequirement();
			MyUtil.copyValidBeanToDestBean(requireForm, requirement);
			requirement.setDate(new Date());
			requirement.setUser(user);
			requirement.setStatus(GlobalDefs.WAITE);
			requirementService.update(requirement);
			return "redirect:/admin/requirement/list";
		}
		
	}
	
	@RequestMapping(value="/admin/requirement/delete",method = RequestMethod.POST)
	public String deleteRequirement(@RequestParam("require_id") Long require_id){
		requirementService.delete(require_id);
		return "redirect:/admin/requirement/list";
	}
	
}
