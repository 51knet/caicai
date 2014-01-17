package com.knet51.patents.controllers.admin.technology;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.technology.TechnologyService;

public class TechnologyDetailController {
	private static final Logger logger = LoggerFactory.getLogger(TechnologyDetailController.class);
	@Autowired
	private TechnologyService technologyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/technology/add", method = RequestMethod.POST)
	public String addTechnology(@Valid TechnologyForm techForm,BindingResult validResult,HttpSession session,Model model ){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/technology/new";
		}else{
			Technology technology = new Technology();
			technology.setAchievement(techForm.getAchievement());
			technology.setAdvantage(techForm.getAdvantage());
			technology.setApplyArea(techForm.getApplyArea());
			technology.setContent(techForm.getContent());
			technology.setCooperation(techForm.getCooperation());
			technology.setDemand(techForm.getDemand());
			technology.setDepartment(techForm.getDepartment());
			technology.setInventer(techForm.getInventer());
			technology.setMaturity(techForm.getMaturity());
			technology.setPhone(techForm.getPhone());
			technology.setProgress(techForm.getProgress());
			technology.setTechField(techForm.getTechField());
			technology.setTechName(techForm.getTechName());
			technology.setTechType(techForm.getTechType());
			technology.setUser(userInfo.getUser());
			technology.setStatus(GlobalDefs.TECH_WAITE);
			
			technologyService.create(technology);
		}
		return "redirect:/admin/technology/list";
	}
	
	@RequestMapping(value="/admin/technology/edit/add", method = RequestMethod.POST)
	public String updateTechnology(@Valid TechnologyForm techForm,BindingResult validResult,
			HttpSession session,Model model, @RequestParam("tech_id") Long tech_id){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/technology/edit/"+tech_id;
		}else{
			Technology technology = technologyService.findOne(tech_id);
			technology.setAchievement(techForm.getAchievement());
			technology.setAdvantage(techForm.getAdvantage());
			technology.setApplyArea(techForm.getApplyArea());
			technology.setContent(techForm.getContent());
			technology.setCooperation(techForm.getCooperation());
			technology.setDemand(techForm.getDemand());
			technology.setDepartment(techForm.getDepartment());
			technology.setInventer(techForm.getInventer());
			technology.setMaturity(techForm.getMaturity());
			technology.setPhone(techForm.getPhone());
			technology.setProgress(techForm.getProgress());
			technology.setTechField(techForm.getTechField());
			technology.setTechName(techForm.getTechName());
			technology.setTechType(techForm.getTechType());
			technology.setUser(userInfo.getUser());
			technology.setStatus(GlobalDefs.TECH_WAITE);
			
			technologyService.update(technology);
		}
		return "redirect:/admin/technology/list";
	}
	
	@RequestMapping(value="/admin/technology/delete",method = RequestMethod.POST)
	public String destoryPatent(@RequestParam("tech_id") Long tech_id){
		technologyService.delete(tech_id);
		return "redirect:/admin/technology/list";
	}
}
