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
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;
import com.knet51.ccweb.jpa.entities.requirement.Requirement;
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.patent.PatentService;
import com.knet51.courses.jpa.services.patent.PatentTypeService;
import com.knet51.courses.jpa.services.projects.ProjectsService;
import com.knet51.courses.jpa.services.requirement.PatentRequirementService;
import com.knet51.courses.jpa.services.requirement.TechRequirementService;
import com.knet51.courses.jpa.services.technology.TechnologyService;
import com.knet51.courses.util.ajax.AjaxValidationEngine;
import com.knet51.courses.util.ajax.ValidationResponse;
import com.knet51.courses.util.mailSender.MailSender;


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
	@Autowired
	private ProjectsService projectsService;
	
	
	@RequestMapping(value="/fastupload", method = RequestMethod.GET)
	public String showFastUploadPage(Model model){
		List<PatentType> patentTypeList = patentTypeService.findAllPatentType();
		model.addAttribute("patentTypeList", patentTypeList);
		
		Map<String, String> techField = GlobalDefs.getTechField();
		model.addAttribute("techField", techField);
		
		Map<String,String> projectsField = GlobalDefs.getProjectsField();
		model.addAttribute("projectsField", projectsField);
		
		return "fastupload";
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
	
	@RequestMapping(value="/patentrequire/add",method = RequestMethod.POST)
	public String createPatentRequire(@Valid PatentRequireForm requireForm,BindingResult validResult,HttpSession session,
			Model model,@RequestParam("patentType") Long type_id){
		
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/fastupload";
		}else{
			String fastEmail = requireForm.getPatentReqEmail();
			User user = validUser(fastEmail);
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
			return "redirect:/fastupload/success";
		}
		
	}
	
	@RequestMapping(value="/projects/add",method = RequestMethod.POST)
	public String createProjects(@Valid ProjectsForm projectsForm,@RequestParam("industry") String industry,
			BindingResult validResult,HttpSession session,Model model){
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/fastupload";
		}else{
			String fastEmail = projectsForm.getProjectsEmail();
			User user = validUser(fastEmail);
			Projects projects = new Projects();
			projects.setCompanyName(projectsForm.getProjectsCompany());
			projects.setContent(projectsForm.getProjectsContent());
			projects.setEmpNumber(projectsForm.getEmpNumber());
			projects.setIndustry(industry);
			projects.setLocation(projectsForm.getLocation());
			projects.setProgress(projectsForm.getProjectsProgress());
			projects.setProjectName(projectsForm.getProjectsName());
			projects.setTotalMoney(Long.parseLong(projectsForm.getTotalMoney()));
			projects.setBoss(projectsForm.getProjectsBoss());
			projects.setPhone(projectsForm.getProjectsPhone());
			projects.setComplete(GlobalDefs.WAITE);
			projects.setCurrentMoney(0L);
			projects.setUser(user);
			Integer maxInvest = Integer.parseInt(projectsForm.getMaxInvestNum());
			maxInvest = maxInvest>=20?20:maxInvest;
			projects.setMaxInvestNum(maxInvest);
			Integer minMoney = Integer.parseInt(projectsForm.getMinMoney());
			minMoney = minMoney<0?1:minMoney;
			projects.setMinMoney(minMoney);
			projects.setDate(new Date());
			projects.setStatus(GlobalDefs.WAITE);
			projects = projectsService.create(projects);
			
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
		User user = userService.getValidEmail(fastEmail);

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
