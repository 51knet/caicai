package com.knet51.courses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.Activity;
import com.knet51.courses.jpa.services.activity.ActivityService;

@Controller
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value="/activity/list")
	public String showActivity(Model model,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		Page<Activity> page = activityService.findAllPage(pageNumber, pageSize);
		List<Activity> activityList = activityService.findAllList();
		model.addAttribute("page", page);
		model.addAttribute("activityCount", activityList.size());
		return "activity.list";
	}
	
	@RequestMapping(value="/activity/view/{activity_id}")
	public String showActivityDetail(@PathVariable Long activity_id,Model model){
		Activity activity = activityService.findOne(activity_id);
		model.addAttribute("activity", activity);
		return "activity.view";
	}
}
