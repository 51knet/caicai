package com.graphene.web.controller.admin.kefu.requirement;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.service.requirement.TechRequireService;



@Controller
public class KefuRequirementController {
	
	@Autowired
	private TechRequireService requirementService;

	
	@RequestMapping(value="/admin/kefu/requirement/list/{status}")
	public String showAllRequirement(@PathVariable String status,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<TechRequirement> page = null;
		if(status.equals("pass")){
			page = requirementService.findRequireByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		}else if(status.equals("waite")){
			page = requirementService.findRequireByStatus(pageNumber, pageSize, GlobalDefs.WAITE);
		}else{
			page = requirementService.findRequireAll(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.requirement.list";
	}


	@RequestMapping(value="/admin/kefu/requirement/view/{require_id}")
	public String showRequirementDetail(Model model,HttpSession session, @PathVariable Long require_id){
		TechRequirement requirement = requirementService.findOne(require_id);
		model.addAttribute("requirement", requirement);
		return "admin.kefu.requirement.view";
	}
	
	
	@RequestMapping(value="/admin/kefu/requirement/status/change")
	public @ResponseBody boolean changeRequirementStatus(@RequestParam("id") Long require_id, @RequestParam("status") Integer status){
		boolean flag = false;
		TechRequirement requirement = requirementService.findOne(require_id);
		if(requirement!= null && status!= null){
			if(status.equals(GlobalDefs.PASS)){
				requirement.setStatus(GlobalDefs.WAITE);
			}else{
				requirement.setStatus(GlobalDefs.PASS);
			}
		}
		TechRequirement newRequirement = requirementService.update(requirement);
		
		if(newRequirement != null){
			flag = true;
		}
		return flag;
	}
	
}
