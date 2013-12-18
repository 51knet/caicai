package com.knet51.patents.controllers.admin.patent;

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

import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.patent.PatentFieldService;
import com.knet51.patents.jpa.services.patent.PatentService;
import com.knet51.patents.jpa.services.patent.PatentTypeService;
@Controller
public class PatentDetailController {
	private static final Logger logger = LoggerFactory.getLogger(PatentDetailController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private PatentTypeService patentTypeService;
	@Autowired
	private PatentFieldService patentFieldService;
	@RequestMapping(value="/admin/patent/add",method = RequestMethod.POST)
	public String addPatent(@Valid PatentForm patentForm, BindingResult validResult,HttpSession session,
			@RequestParam("patentType") Long type_id,Model model){
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/patent/new";
		}else{	
			Patent patent = new Patent();
			PatentType patentType = patentTypeService.findOne(type_id);
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			patent.setAddress(patentForm.getAddress());
			patent.setAgency(patentForm.getAgency());
			patent.setAgent(patentForm.getAgent());
			patent.setApplicant(patentForm.getApplicant());
			patent.setApplicationDate(patentForm.getApplicationDate());
			patent.setClassNum(patentForm.getClassNum());
			patent.setInventer(patentForm.getInventer());
			patent.setMainClassNum(patentForm.getMainClassNum());
			patent.setPatentName(patentForm.getPatentName());
			patent.setPatentNum(patentForm.getPatentNum());
			patent.setPublishDate(patentForm.getPublishDate());
			patent.setPublishNum(patentForm.getPublishNum());
			patent.setSummary(patentForm.getSummary());
			patent.setStatus(GlobalDefs.PATENT_STORE);
			patent.setPatentType(patentType);
			patent.setUser(userInfo.getUser());
			patent.setPatentField(patentForm.getPatentField());
			
			
			patentService.create(patent);
			model.addAttribute("patent", patent);
			return "admin."+userInfo.getRole()+".patent.view";
		}
	}
	
	@RequestMapping(value="/admin/patent/edit/add",method = RequestMethod.POST)
	public String editPatent(@Valid PatentForm patentForm, BindingResult validResult,HttpSession session,
			@RequestParam("patentType") Long type_id,Model model){
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/patent/edit/"+patentForm.getPatentNum();
		}else{	
			Patent patent = patentService.findOne(patentForm.getPatentNum());
			PatentType patentType = patentTypeService.findOne(type_id);
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			patent.setAddress(patentForm.getAddress());
			patent.setAgency(patentForm.getAgency());
			patent.setAgent(patentForm.getAgent());
			patent.setApplicant(patentForm.getApplicant());
			patent.setApplicationDate(patentForm.getApplicationDate());
			patent.setClassNum(patentForm.getClassNum());
			patent.setInventer(patentForm.getInventer());
			patent.setMainClassNum(patentForm.getMainClassNum());
			patent.setPatentName(patentForm.getPatentName());
			patent.setPatentNum(patentForm.getPatentNum());
			patent.setPublishDate(patentForm.getPublishDate());
			patent.setPublishNum(patentForm.getPublishNum());
			patent.setSummary(patentForm.getSummary());
			patent.setStatus(GlobalDefs.PATENT_STORE);
			patent.setPatentType(patentType);
			patent.setUser(userInfo.getUser());
			patent.setPatentField(patentForm.getPatentField());
			
			patentService.update(patent);
			model.addAttribute("patent", patent);
			return "admin."+userInfo.getRole()+".patent.view";
		}
	}
	
	@RequestMapping(value="/admin/patent/delete",method = RequestMethod.POST)
	public String destoryPatent(@RequestParam("patentNum") String patentNum){
		patentService.delete(patentNum);
		return "redirect:/admin/patent/list";
	}
}
