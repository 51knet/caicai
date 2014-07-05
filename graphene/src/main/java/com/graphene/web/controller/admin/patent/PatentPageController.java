package com.graphene.web.controller.admin.patent;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.graphene.web.jpa.entity.patent.Patent;
import com.graphene.web.jpa.entity.patent.PatentField;
import com.graphene.web.jpa.entity.patent.PatentType;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;
import com.graphene.web.service.patent.PatentFieldService;
import com.graphene.web.service.patent.PatentService;
import com.graphene.web.service.patent.PatentTypeService;
import com.graphene.web.util.ajax.AjaxValidationEngine;
import com.graphene.web.util.ajax.ValidationResponse;


@Controller
public class PatentPageController {
	private static final Logger logger = LoggerFactory.getLogger(PatentPageController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private PatentTypeService patentTypeService;
	@Autowired
	private PatentFieldService patentFieldService;
	
	@RequestMapping("/admin/patent/list")
	public String showPatentList(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("===== into patent list controller ====");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
	
		
		try {
			
			Page<Patent> page = patentService.findPatentByUser(pageNumber, pageSize, user);
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin."+userInfo.getRole()+".patent.list";
	}
	
	@RequestMapping("/admin/patent/new")
	public String addPatentList(HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<PatentType> pTypeList = patentTypeService.findAllPatentType();
		model.addAttribute("pTypeList", pTypeList);
		List<PatentField> pFieldList = patentFieldService.findAll();
		model.addAttribute("pFieldList", pFieldList);
		return "admin."+userInfo.getRole()+".patent.new";
	}
	
	@RequestMapping("/admin/patent/edit/{p_id}")
	public String showPatentPreview(HttpSession session,Model model,@PathVariable Long p_id){
		Patent patent = patentService.findOne(p_id);
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		
		if(!userInfo.getId().equals(patent.getUser().getId())){
			return "redirect:/admin";
		}
		List<PatentType> pTypeList = patentTypeService.findAllPatentType();
		List<PatentField> pFieldList = patentFieldService.findAll();
		model.addAttribute("pTypeList", pTypeList);
		model.addAttribute("pFieldList", pFieldList);
		model.addAttribute("patent", patent);
		return "admin."+userInfo.getRole()+".patent.edit";
	}
	@RequestMapping(value="/admin/patent/view/{patent_id}")
	public String showPatentDetail(Model model,HttpSession session,@PathVariable Long patent_id){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Patent patent = patentService.findOne(patent_id);
		model.addAttribute("patent", patent);
		return "admin."+userInfo.getRole()+".patent.view";
	}
	
	@RequestMapping(value = "/admin/patent/patentInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormAjaxJson(@Valid PatentForm patentForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/patent/edit/new/patentInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormUpdateAjaxJson(@Valid PatentForm patentForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
}
