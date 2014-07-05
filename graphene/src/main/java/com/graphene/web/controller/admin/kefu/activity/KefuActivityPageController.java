package com.graphene.web.controller.admin.kefu.activity;

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

import com.graphene.web.jpa.entity.Activity;
import com.graphene.web.service.activity.ActivityService;
import com.graphene.web.util.ajax.AjaxValidationEngine;
import com.graphene.web.util.ajax.ValidationResponse;



@Controller
public class KefuActivityPageController {
	@Autowired
	private ActivityService activityService;
	@RequestMapping(value="/admin/kefu/activity/list")
	public String showAllActivitiesPage(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		Page<Activity> page = activityService.findAllPage(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.kefu.activity.list";
	}
	
	@RequestMapping(value="/admin/kefu/activity/create")
	public String createNewActivityPage(){
		
		return "admin.kefu.activity.create";
	}
	
	@RequestMapping(value = "/admin/kefu/activity/createActivityAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse activityInfoFormAjaxJson(@Valid ActivityForm 
			activityForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	
	@RequestMapping(value="/admin/kefu/activity/view/{id}")
	public String showActivityDetailPage(@PathVariable Long id, Model model){
		Activity activity = activityService.findOne(id);
		model.addAttribute("activity", activity);
		
		return "admin.kefu.activity.view";
	}
	
	@RequestMapping(value="/admin/kefu/activity/edit/{id}")
	public String editActivityDetailPage(@PathVariable Long id, Model model){
		Activity activity = activityService.findOne(id);
		model.addAttribute("activity", activity);
		
		return "admin.kefu.activity.edit";
	}
	
	@RequestMapping(value = "/admin/kefu/activity/edit/updateActivityAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse editactivityInfoFormAjaxJson(@Valid ActivityForm 
			activityForm, BindingResult result,@PathVariable Long id) {
		return AjaxValidationEngine.process(result);
	}
}
