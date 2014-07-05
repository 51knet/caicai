package com.graphene.web.controller.front.fastupload;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.patent.Patent;
import com.graphene.web.jpa.entity.patent.PatentField;
import com.graphene.web.jpa.entity.patent.PatentType;
import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.jpa.entity.tech.Technology;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;
import com.graphene.web.service.patent.PatentFieldService;
import com.graphene.web.service.patent.PatentService;
import com.graphene.web.service.patent.PatentTypeService;
import com.graphene.web.service.requirement.TechRequireService;
import com.graphene.web.service.tech.TechnologyService;
import com.graphene.web.util.ajax.AjaxValidationEngine;
import com.graphene.web.util.ajax.ValidationResponse;
import com.graphene.web.util.mailSender.MailSender;



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
	private TechRequireService techRequireService;
	@Autowired
	private PatentFieldService patentFieldService;
	
	@RequestMapping(value="/front/fastupload")
	public String showFastUploadPage(Model model){
		List<PatentType> patentTypeList = patentTypeService.findAllPatentType();
		model.addAttribute("patentTypeList", patentTypeList);
		
		Map<String, String> techField = GlobalDefs.getTechField();
		model.addAttribute("techField", techField);
		
		List<PatentField> pFieldList = patentFieldService.findAll();
		model.addAttribute("pFieldList", pFieldList);
		
		return "front.fastupload";
	}
	@RequestMapping(value="/patent/add",method = RequestMethod.POST)
	public String createPatent(@Valid PatentForm patentForm,BindingResult validResult,HttpSession session,
			Model model,@RequestParam("patentType") Long type_id,@RequestParam("country") Integer country){
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/fastupload";
		}else{
			String fastEmail = patentForm.getPatentEmail();
			User user = validUser(fastEmail);
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
			patent.setPhone(patentForm.getPatentPhone());
			patent.setFocus(GlobalDefs.HOME_FOCUS_NOT);
			if(country.equals(GlobalDefs.FOREIGN)){
				patent.setCountry(GlobalDefs.FOREIGN);
			}else{
				patent.setCountry(GlobalDefs.CHINA);
			}
			patent.setPatentField(patentForm.getPatentField());
			
			
			patentService.create(patent);
			return "redirect:/fastupload/success";
		}
		
	}
	
	@RequestMapping(value="/technology/add",method = RequestMethod.POST)
	public String createTech(@Valid TechnologyForm techForm,BindingResult validResult,HttpSession session,
			Model model){
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/fastupload";
		}else{
			
			try {
				String fastEmail = techForm.getTechEmail();
				User user = validUser(fastEmail);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/fastupload/success";
		}
		
	}
	
	

	@RequestMapping(value="/techrequire/add",method = RequestMethod.POST)
	public String createrequirement(@Valid TechRequireForm requireForm,BindingResult validResult,HttpSession session,
			Model model){
		
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/fastupload";
		}else{
			String fastEmail = requireForm.getTechReqEmail();
			User user = validUser(fastEmail);
			TechRequirement requirement = new TechRequirement();
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
			return "redirect:/fastupload/success";
		}
		
	}
	
	
	
	@RequestMapping(value="/fastupload/success", method=RequestMethod.GET)
	public String FastUploadSuccess(){
		return "fastupload.success";
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
	
	@RequestMapping(value = "/fastupload/projectsAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse projectsFormAjaxJson(@Valid ProjectsForm projectsForm, BindingResult result) {
		logger.info(" valid result="+result.toString());
		return AjaxValidationEngine.process(result);
	}
	
	private User validUser(String fastEmail){
		User user = userService.findByEmailAddress(fastEmail);

		if(user == null){
			user = new User();
			user.setEmail(fastEmail);
			user.setPassword(GlobalDefs.DEFAULT_PWD);
			user.setName(fastEmail);
			user.setPhoto_url(GlobalDefs.DEFAULT_PHOTO_URL);
			user.setRole(GlobalDefs.USER_ROLE);
			user.setRandomUrl("pass");
			user = userService.createUser(user);
			MailSender.getInstance().SendFastUploadMail(fastEmail);
			logger.info("==== user not exist "+user.getName());
		}else{
			logger.info("==== user is exist "+user.getName());
		}

		return user;
	}
}
