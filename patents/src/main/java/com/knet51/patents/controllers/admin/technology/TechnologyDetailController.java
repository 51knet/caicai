package com.knet51.patents.controllers.admin.technology;

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

import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.technology.TechnologyService;
@Controller
public class TechnologyDetailController {
	private static final Logger logger = LoggerFactory.getLogger(TechnologyDetailController.class);
	@Autowired
	private TechnologyService technologyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/technology/new/add", method = RequestMethod.POST)
	public String addTechnology(@Valid TechnologyForm techForm,BindingResult validResult,HttpSession session,Model model){
		
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		try {
			if(validResult.hasErrors()){
				logger.info("====="+validResult.toString());
				return "redirect:/admin/technology/new";
			}else{
				Technology technology = new Technology();
				technology.setAchievement(techForm.getAchievement());
				technology.setAdvantage(techForm.getAdvantage());
				technology.setApplyArea(techForm.getApplyArea());
				technology.setContents(techForm.getContents());
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
				technology.setStatus(GlobalDefs.WAITE);
				technology.setFocus(GlobalDefs.HOME_FOCUS_NOT);
				technology.setDate(new Date());
				technologyService.create(technology);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/technology/list";
	}
	
	
	@RequestMapping(value="/admin/technology/delete",method = RequestMethod.POST)
	public String destoryPatent(@RequestParam("tech_id") Long tech_id){
		try {
			technologyService.delete(tech_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/admin/technology/list";
	}
	
	@RequestMapping(value="/admin/technology/edit/edit", method = RequestMethod.POST)
	public String editTechnology(@Valid TechnologyForm techForm,BindingResult validResult,HttpSession session,Model model,@RequestParam("tech_id") Long tech_id){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		try {
			if(validResult.hasErrors()){
				logger.info("====="+validResult.toString());
				return "redirect:/admin/technology/edit/"+tech_id;
			}else{
				Technology technology =  technologyService.findOne(tech_id);	
				technology.setAchievement(techForm.getAchievement());
				technology.setAdvantage(techForm.getAdvantage());
				technology.setApplyArea(techForm.getApplyArea());
				technology.setContents(techForm.getContents());
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
				technology.setStatus(GlobalDefs.WAITE);
				technology.setDate(new Date());
				technologyService.update(technology);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/technology/list";
	}
}
