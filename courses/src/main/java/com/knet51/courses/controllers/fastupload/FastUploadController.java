package com.knet51.courses.controllers.fastupload;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;
import com.knet51.ccweb.jpa.entities.requirement.Requirement;
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.patent.PatentService;
import com.knet51.courses.jpa.services.patent.PatentTypeService;
import com.knet51.courses.jpa.services.requirement.PatentRequirementService;
import com.knet51.courses.jpa.services.requirement.TechRequirementService;
import com.knet51.courses.jpa.services.technology.TechnologyService;
import com.knet51.courses.util.ajax.AjaxValidationEngine;
import com.knet51.courses.util.ajax.ValidationResponse;


@Controller
public class FastUploadController {
	
	private static Logger logger = LoggerFactory.getLogger(FastUploadController.class);
	
	@Autowired
	private PatentTypeService patentTypeService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private TechnologyService techService;
	@Autowired
	private PatentRequirementService patentRequireService;
	@Autowired
	private TechRequirementService techRequireService;
	
	
	@RequestMapping(value="/fastupload", method = RequestMethod.GET)
	public String showFastUploadPage(Model model){
		List<PatentType> patentTypeList = patentTypeService.findAllPatentType();
		model.addAttribute("patentTypeList", patentTypeList);
		
		Map<String, String> techField = GlobalDefs.getTechField();
		model.addAttribute("techField", techField);
		
		return "fastupload";
	}
	
	@RequestMapping(value="/patent/add",method = RequestMethod.POST)
	public String createPatent(@Valid PatentForm patentForm,BindingResult validResult,HttpSession session,
			Model model,@RequestParam("patentType") Long type_id,@RequestParam("country") Integer country){
		User user = userService.getValidEmail("tim@apple.com");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/fastupload";
		}else{
			Patent patent = new Patent();
			PatentType patentType = patentTypeService.findOne(type_id);
			patent.setAddress(patentForm.getPatentAddress());
			patent.setAgency(patentForm.getAgency());
			patent.setAgent(patentForm.getAgent());
			patent.setApplicant(patentForm.getApplicant());
			patent.setApplicationDate(patentForm.getApplicationDate());
			patent.setClassNum(patentForm.getClassNum());
			patent.setInventer(patentForm.getPatentInventer());
			patent.setMainClassNum(patentForm.getMainClassNum());
			patent.setPatentName(patentForm.getPatentName());
			patent.setPatentNum(patentForm.getPatentNum());
			patent.setPublishDate(patentForm.getPublishDate());
			patent.setPublishNum(patentForm.getPublishNum());
			patent.setSummary(patentForm.getSummary());
			patent.setStatus(GlobalDefs.WAITE);
			patent.setPatentType(patentType);
			patent.setUser(user);
			patent.setFocus(GlobalDefs.HOME_FOCUS_NOT);
			if(country.equals(GlobalDefs.PATENT_FOREIGN)){
				patent.setCountry(GlobalDefs.PATENT_FOREIGN);
			}else{
				patent.setCountry(GlobalDefs.PATENT_CHINA);
			}
			patent.setPatentField(patentForm.getPatentField());
			
			
			patentService.create(patent);
			return "redirect:/fastupload/success";
		}
		
	}
	
	@RequestMapping(value="/technology/add",method = RequestMethod.POST)
	public String createTech(@Valid TechnologyForm techForm,BindingResult validResult,HttpSession session,
			Model model){
		User user = userService.getValidEmail("tim@apple.com");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/fastupload";
		}else{
			Technology technology = new Technology();
			technology.setAchievement(techForm.getAchievement());
			technology.setAdvantage(techForm.getAdvantage());
			technology.setApplyArea(techForm.getApplyArea());
			technology.setContents(techForm.getTechContents());
			technology.setCooperation(techForm.getCooperation());
			technology.setDemand(techForm.getDemand());
			technology.setDepartment(techForm.getDepartment());
			technology.setInventer(techForm.getTechInventer());
			technology.setMaturity(techForm.getMaturity());
			technology.setPhone(techForm.getTechPhone());
			technology.setProgress(techForm.getProgress());
			technology.setTechField(techForm.getTechField());
			technology.setTechName(techForm.getTechName());
			technology.setTechType(techForm.getTechType());
			technology.setUser(user);
			technology.setStatus(GlobalDefs.WAITE);
			technology.setFocus(GlobalDefs.HOME_FOCUS_NOT);
			technology.setDate(new Date());
			techService.create(technology);
			return "redirect:/fastupload/success";
		}
		
	}
	
	@RequestMapping(value="/patentrequire/add",method = RequestMethod.POST)
	public String createPatentRequire(@Valid PatentRequireForm requireForm,BindingResult validResult,HttpSession session,
			Model model,@RequestParam("patentType") Long type_id){
		User user = userService.getValidEmail("tim@apple.com");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/patentRequirement/new";
		}else{
			PatentType type = patentTypeService.findOne(type_id);
			
			PatentRequirement requirement = new PatentRequirement();
			requirement.setTitle(requireForm.getPatentReqTitle());
			requirement.setRequirementField(requireForm.getPatentReqField());
			requirement.setPatentType(type);
			requirement.setContent(requireForm.getPatentReqContent());
			requirement.setDate(new Date());
			requirement.setMoney(requireForm.getPatentReqMoney());
			requirement.setCooperation(requireForm.getPatentReqCooperation());
			
			requirement.setCompany(requireForm.getPatentReqCompany());
			requirement.setContact(requireForm.getPatentReqContact());
			requirement.setPhone(requireForm.getPatentReqPhone());
			requirement.setFax(requireForm.getPatentReqFax());
			requirement.setEmail(requireForm.getPatentReqEmail());
			requirement.setUser(user);
			requirement.setStatus(GlobalDefs.WAITE);
			patentRequireService.create(requirement);
			
			return "redirect:/admin/patentRequirement/list";
		}
		
	}
	
	@RequestMapping(value="/techrequire/add",method = RequestMethod.POST)
	public String createrequirement(@Valid TechRequireForm requireForm,BindingResult validResult,HttpSession session,
			Model model){
		User user = userService.getValidEmail("tim@apple.com");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/requirement/new";
		}else{
			Requirement requirement = new Requirement();
			requirement.setTitle(requireForm.getTechReqTitle());
			requirement.setContent(requireForm.getTechReqContent());
			requirement.setDate(new Date());
			requirement.setUser(user);
			requirement.setEndTime(requireForm.getTechReqEndTime());
			
			requirement.setAddress(requireForm.getTechReqAddress());
			requirement.setCompany(requireForm.getTechReqCompany());
			requirement.setMoney(requireForm.getTechReqMoney());
			requirement.setPhone(requireForm.getTechReqPhone());
			requirement.setName(requireForm.getTechReqName());
			requirement.setStatus(GlobalDefs.WAITE);
			
			techRequireService.create(requirement);
			return "redirect:/admin/requirement/list";
		}
		
	}
	
	@RequestMapping(value = "/fastupload/patentInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentFormAjaxJson(@Valid PatentForm patentForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/fastupload/techInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse techFormAjaxJson(@Valid TechnologyForm technologyForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/fastupload/patentRequireAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentReqFormAjaxJson(@Valid PatentRequireForm patentRequireForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/fastupload/techRequireAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse techReqFormAjaxJson(@Valid TechRequireForm techRequireForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
}
