package com.knet51.patents.controllers.admin.kefu.rzfh;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.knet51.ccweb.jpa.entities.projects.Rzfh;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.projects.RzfhService;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;
@Controller
public class KefuRzfhPageController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RzfhService rzfhService;
	
	@RequestMapping(value="/admin/kefu/rzfh/list/{types}")
	public String showAllRzfh(@PathVariable String types,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<Rzfh> page = null;
		if(types.equals("rzjg")){
			page = rzfhService.findRzfhByUserAndTypes(userInfo.getUser(), GlobalDefs.RZJG, pageNumber, pageSize);	
		}else if(types.equals("fhyq")){
			page = rzfhService.findRzfhByUserAndTypes(userInfo.getUser(), GlobalDefs.FHYQ, pageNumber, pageSize);
		}else{
			page = rzfhService.findAll(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.rzfh.list";
	}
	
	@RequestMapping(value="/admin/kefu/rzfh/new")
	public String createRzfhPage(){
		
		return "admin.kefu.rzfh.create";
	}
	
	@RequestMapping(value = "/admin/kefu/rzfh/createrzfhAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse activityInfoFormAjaxJson(@Valid RzfhForm 
			rzfhForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value="/admin/kefu/rzfh/edit/{id}")
	public String editActivityDetailPage(@PathVariable Long id, Model model){
		Rzfh rzfh = rzfhService.findOne(id);
		model.addAttribute("rzfh", rzfh);
		
		return "admin.kefu.rzfh.edit";
	}
	
	@RequestMapping(value = "/admin/kefu/rzfh/edit/updaterzfhAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse editEzfhInfoFormAjaxJson(@Valid RzfhForm 
			rzfhForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
}
