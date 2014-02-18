package com.knet51.patents.controllers.admin.requirement;

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

import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.patent.PatentTypeService;
import com.knet51.patents.jpa.services.requirement.PatentRequirementService;
import com.knet51.patents.jpa.services.requirement.RequirTypeService;
import com.knet51.patents.jpa.services.requirement.RequirementService;
import com.knet51.ccweb.jpa.entities.RequirType;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;
import com.knet51.ccweb.jpa.entities.requirement.Requirement;

@Controller
public class RequireDetailController {
	private static Logger logger = 
			LoggerFactory.getLogger(RequireDetailController.class);
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private RequirTypeService requirTypeService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatentRequirementService patentRequirementService;
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
			Requirement requirement = new Requirement();
			requirement.setTitle(requireForm.getTitle());
			requirement.setContent(requireForm.getContent());
			requirement.setDate(new Date());
			requirement.setUser(user);
			requirement.setEndTime(requireForm.getEndTime());
			
			requirement.setAddress(requireForm.getAddress());
			requirement.setCompany(requireForm.getCompany());
			requirement.setMoney(requireForm.getMoney());
			requirement.setPhone(requireForm.getPhone());
			requirement.setName(requireForm.getName());
			requirement.setStatus(GlobalDefs.REQUIREMENT_WAITE);
			
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
			
			Requirement requirement = requirementService.findOne(require_id);
			requirement.setTitle(requireForm.getTitle());
			requirement.setContent(requireForm.getContent());
			requirement.setDate(new Date());
			requirement.setUser(user);
			requirement.setEndTime(requireForm.getEndTime());
			
			requirement.setAddress(requireForm.getAddress());
			requirement.setCompany(requireForm.getCompany());
			requirement.setMoney(requireForm.getMoney());
			requirement.setPhone(requireForm.getPhone());
			requirement.setName(requireForm.getName());
			requirement.setStatus(GlobalDefs.REQUIREMENT_WAITE);
			requirementService.update(requirement);
			return "redirect:/admin/requirement/list";
		}
		
	}
	
	@RequestMapping(value="/admin/requirement/delete",method = RequestMethod.POST)
	public String deleteRequirement(@RequestParam("require_id") Long require_id){
		requirementService.delete(require_id);
		return "redirect:/admin/requirement/list";
	}
	
	@RequestMapping(value="/admin/patentRequirement/add",method = RequestMethod.POST)
	public String createPatentRequirement(@Valid PatentRequirementForm requireForm,BindingResult validResult,HttpSession session,
			@RequestParam("patentType") Long type_id,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/patentRequirement/new";
		}else{
			PatentType type = patentTypeService.findOne(type_id);
			
			PatentRequirement requirement = new PatentRequirement();
			requirement.setTitle(requireForm.getTitle());
			requirement.setRequirementField(requireForm.getRequirementField());
			requirement.setPatentType(type);
			requirement.setContent(requireForm.getContent());
			requirement.setDate(new Date());
			requirement.setMoney(requireForm.getMoney());
			requirement.setCooperation(requireForm.getCooperation());
			
			requirement.setCompany(requireForm.getCompany());
			requirement.setContact(requireForm.getContact());
			requirement.setPhone(requireForm.getPhone());
			requirement.setFax(requireForm.getFax());
			requirement.setEmail(requireForm.getEmail());
			requirement.setUser(user);
			requirement.setStatus(GlobalDefs.REQUIREMENT_WAITE);
			patentRequirementService.create(requirement);
			
			return "redirect:/admin/patentRequirement/list";
		}
		
	}
	
	@RequestMapping(value="/admin/patentRequirement/edit/edit",method = RequestMethod.POST)
	public String updatePatentRequirement(@Valid  PatentRequirementForm requireForm,BindingResult validResult,HttpSession session,
			@RequestParam("patentType") Long type_id,@RequestParam("require_id") Long require_id,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/requirement/edit/"+require_id;
		}else{
			PatentType type = patentTypeService.findOne(type_id);
			PatentRequirement requirement = patentRequirementService.findOne(require_id);
			requirement.setTitle(requireForm.getTitle());
			requirement.setRequirementField(requireForm.getRequirementField());
			requirement.setPatentType(type);
			requirement.setContent(requireForm.getContent());
			requirement.setDate(new Date());
			requirement.setMoney(requireForm.getMoney());
			requirement.setCooperation(requireForm.getCooperation());
			
			requirement.setCompany(requireForm.getCompany());
			requirement.setContact(requireForm.getContact());
			requirement.setPhone(requireForm.getPhone());
			requirement.setFax(requireForm.getFax());
			requirement.setEmail(requireForm.getEmail());
			requirement.setUser(user);
			patentRequirementService.update(requirement);
			return "redirect:/admin/patentRequirement/list";
		}
		
	}
	
	@RequestMapping(value="/admin/patentRequirement/delete",method = RequestMethod.POST)
	public String deletePatentRequirement(@RequestParam("require_id") Long require_id){
		try {
			patentRequirementService.delete(require_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/patentRequirement/list";
		
	}
}
