package com.knet51.courses.controllers.requirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;
import com.knet51.ccweb.jpa.entities.requirement.Requirement;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.requirement.PatentRequirementService;
import com.knet51.courses.jpa.services.requirement.RequirementService;
@Controller
public class RequirementController {
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private PatentRequirementService patentRequirementService;

	
	@RequestMapping(value="/requirement/{require_type}/list")
	public String showRequirementList(@PathVariable String require_type,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,Model model){
		
		if(require_type.equals("patent")){
			Page<PatentRequirement> page = patentRequirementService.findAllByStatus(pageNumber, pageSize, GlobalDefs.REQUIREMENT_PASS);
			model.addAttribute("page", page);
		}else if(require_type.equals("technology")){
			Page<Requirement> page = requirementService.findRequireByStatus(pageNumber, pageSize, GlobalDefs.REQUIREMENT_PASS);
			model.addAttribute("page", page);
		}
		
		
		model.addAttribute("active", require_type);
		return "requirement.list";
	}
	
	@RequestMapping(value="/requirement/{require_type}/view/{require_id}")
	public String showRequirementDetail(@PathVariable String require_type,@PathVariable Long require_id,Model model){
		
		if(require_type.equals("patent")){
			PatentRequirement patentRequirement = patentRequirementService.findOne(require_id);
			model.addAttribute("patentRequirement", patentRequirement);
			model.addAttribute("active", require_type);
			return "patentrequirement.view";
		}else if(require_type.equals("technology")){
			Requirement requirement = requirementService.findOne(require_id);
			model.addAttribute("requirement", requirement);
			model.addAttribute("active", require_type);
			return "requirement.view";
		}else{
			return "redirect:/requirement/"+require_type+"/list";
		}
		
	}
}
