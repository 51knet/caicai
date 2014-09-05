package com.knet51.patents.controllers.admin.patent;

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

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentField;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.patent.PatentFieldService;
import com.knet51.patents.jpa.services.patent.PatentService;
import com.knet51.patents.jpa.services.patent.PatentTypeService;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;
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
			List<Patent> patentList = patentService.findPatentListByUser(user);
			Page<Patent> page = patentService.findPatentByUser(pageNumber, pageSize, user);
			model.addAttribute("patent", patentList);
			model.addAttribute("patentCount", patentList.size());
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
	
	@RequestMapping("/admin/patent/edit")
	public String showPatentPreview(HttpSession session,Model model,@RequestParam(value = "id") String patentNum){
		Patent patent = patentService.findOne(patentNum);
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
	@RequestMapping(value="/admin/patent/view")
	public String showPatentDetail(Model model,HttpSession session, @RequestParam(value = "id") String patentNum){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Patent patent = patentService.findOne(patentNum);
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
