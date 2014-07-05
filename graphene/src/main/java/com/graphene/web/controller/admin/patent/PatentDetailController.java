package com.graphene.web.controller.admin.patent;

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
import com.graphene.web.jpa.entity.patent.Patent;
import com.graphene.web.jpa.entity.patent.PatentType;
import com.graphene.web.service.UserService;
import com.graphene.web.service.patent.PatentFieldService;
import com.graphene.web.service.patent.PatentService;
import com.graphene.web.service.patent.PatentTypeService;
import com.graphene.web.util.MyUtil;

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
			@RequestParam("patentType") Long type_id,@RequestParam("country") Integer country,Model model){
			
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/patent/new";
		}else{	
			Patent patent = new Patent();
			PatentType patentType = patentTypeService.findOne(type_id);
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			MyUtil.copyValidBeanToDestBean(patentForm, patent);
			
			patent.setStatus(GlobalDefs.WAITE);
			patent.setPatentType(patentType);
			patent.setUser(userInfo.getUser());
			patent.setFocus(GlobalDefs.HOME_FOCUS_NOT);
			if(country.equals(GlobalDefs.FOREIGN)){
				patent.setCountry(GlobalDefs.FOREIGN);
			}else{
				patent.setCountry(GlobalDefs.CHINA);
			}
			patent.setPatentField(patentForm.getPatentField());
			
			
			patentService.create(patent);
			model.addAttribute("patent", patent);
			return "admin."+userInfo.getRole()+".patent.view";
		}
	}
	
	@RequestMapping(value="/admin/patent/edit/add",method = RequestMethod.POST)
	public String editPatent(@Valid PatentForm patentForm, BindingResult validResult,HttpSession session,
			@RequestParam("patentType") Long type_id,Model model,@RequestParam("country") Integer country,@RequestParam("patent_id") Long p_id){
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/patent/edit/"+patentForm.getPatentNum();
		}else{	
			Patent patent = patentService.findOne(p_id);
			PatentType patentType = patentTypeService.findOne(type_id);
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			MyUtil.copyValidBeanToDestBean(patentForm, patent);
			patent.setStatus(GlobalDefs.WAITE);
			patent.setPatentType(patentType);
			patent.setUser(userInfo.getUser());
			
			if(country.equals(GlobalDefs.FOREIGN)){
				patent.setCountry(GlobalDefs.FOREIGN);
			}else{
				patent.setCountry(GlobalDefs.CHINA);
			}
			patentService.update(patent);
			model.addAttribute("patent", patent);
			return "admin."+userInfo.getRole()+".patent.view";
		}
	}
	
	@RequestMapping(value="/admin/patent/delete",method = RequestMethod.POST)
	public String destoryPatent(@RequestParam("patent_id")  Long p_id){
		patentService.delete(p_id);
		return "redirect:/admin/patent/list";
	}
}
