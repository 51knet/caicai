package com.knet51.patents.controllers.admin.investcompany;


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

import com.knet51.ccweb.jpa.entities.investcompany.InvestComInfor;
import com.knet51.ccweb.jpa.entities.investcompany.InvestSuccessCase;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.investcompany.InvestComInforService;
import com.knet51.patents.jpa.services.investcompany.InvestSuccessCaseService;
import com.knet51.patents.jpa.services.projects.ProjectsService;
import com.knet51.patents.util.MyUtil;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;

@Controller
@RequestMapping(value="/admin/investcompany")
public class InvestCompanyController {
	@Autowired
	private UserService userService;
	@Autowired
	private InvestComInforService investComInforService;
	@Autowired
	private InvestSuccessCaseService investSuccessCaseService;
	@Autowired
	private ProjectsService projectsService;
	
	
	
	/* investcompany about */
	@RequestMapping(value="/about/new", method=RequestMethod.POST)
	public String updateInvestComInfor(@Valid InvestComInforForm inforForm, BindingResult result,
			HttpSession session, Model model,@RequestParam("cominfor_id") Long cominfo_id){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		InvestComInfor comInfor = investComInforService.findByUser(userInfo.getUser());
		if(comInfor == null){
			if(result.hasErrors()){
				//System.out.println(result.toString());
				return "redirect:/admin/investcompany/about";
			}else{
				comInfor = new InvestComInfor();
				MyUtil.copyValidBeanToDestBean(inforForm,comInfor);
				comInfor.setUser(userInfo.getUser());
				comInfor = investComInforService.create(comInfor);		
				return "redirect:/admin/investcompany/about";
			}
			
		}else{
			MyUtil.copyValidBeanToDestBean(inforForm,comInfor);
			comInfor = investComInforService.update(comInfor);
		}
		return "redirect:/admin/investcompany/about";
	}
	@RequestMapping(value="/about")
	public String showInvestComInfor(HttpSession session, Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		InvestComInfor comInfor = investComInforService.findByUser(userInfo.getUser());
		model.addAttribute("comInfor", comInfor);
		return "admin.investcompany.about";
	}
	
	/* investcompany success cases */
	@RequestMapping(value="/successcase/list")
	public String showSuccessCase(HttpSession session, Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize",defaultValue="20") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<InvestSuccessCase> page = investSuccessCaseService.findSuccessCaseByUser(userInfo.getUser(), pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.investcompany.case.list";
	}
	
	@RequestMapping(value="/successcase/add")
	public String showCreateSuccessCasePage(){
		return "admin.investcompany.case.add";
	}
	
	
	@RequestMapping(value="/successcase/new", method=RequestMethod.POST)
	public String createSuccessCase(HttpSession session,@Valid InvestSuccessCaseForm successCaseForm,BindingResult result){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(result.hasErrors()){
			return "redirect:/admin/investcompany/successcase/add";
		}else{
			InvestSuccessCase successCase = new InvestSuccessCase();
			MyUtil.copyValidBeanToDestBean(successCaseForm, successCase);
			successCase.setUser(userInfo.getUser());
			investSuccessCaseService.create(successCase);
		}
		return "redirect:/admin/investcompany/successcase/list";
	}
	
	@RequestMapping(value="/successcase/edit/{id}")
	public String editInvestcomAnnoPage(@PathVariable Long id,HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		
		try {
			InvestSuccessCase successCase = investSuccessCaseService.findOne(id);
			if(!successCase.getUser().getId().equals(userInfo.getId())){
				return "redirect:/";
			}
			model.addAttribute("successCase", successCase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin.investcompany.case.edit";
	}
	
	@RequestMapping(value="/successcase/edit/edit", method=RequestMethod.POST)
	public String editSuccessCase(@RequestParam("case_id") Long id, Model model,@Valid InvestSuccessCaseForm caseForm, BindingResult result ){
		if(result.hasErrors()){
			return "redirect:/admin/investcompany/successcase/edit/"+id;
		}else{
			try {
				InvestSuccessCase successCase = investSuccessCaseService.findOne(id);
				MyUtil.copyValidBeanToDestBean(caseForm, successCase);
				investSuccessCaseService.update(successCase);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/investcompany/successcase/list";
	}
	
	@RequestMapping(value="/successcase/destory", method = RequestMethod.POST)
	public String teacherAnnoDele( @RequestParam("case_id") Long case_id,HttpSession session, Model m){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		InvestSuccessCase successCase = investSuccessCaseService.findOne(case_id);
		if(!successCase.getUser().getId().equals(userInfo.getId())){
			return "redirect:/";
		}
		investSuccessCaseService.delete(case_id);
		return "redirect:/admin/investcompany/successcase/list";
	}
	
	@RequestMapping(value ="/successcase/caseInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse announcementInfoFormAjaxJson(@Valid InvestSuccessCaseForm successCaseForm , BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	/* invested projects in patents */
	@RequestMapping(value="/projects/list")
	public String showInvestedProjects(HttpSession session, Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize",defaultValue="20") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<Projects> page = projectsService.findOrderProjectsByUser(userInfo.getId(), pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.investcompany.projects.list";
	}
	
	@RequestMapping(value="/projects/view/{id}")
	public String showInvestedProjects(HttpSession session, Model model,@PathVariable Long id){
		Projects project = projectsService.findOne(id); 
		model.addAttribute("projects", project);
		return "admin.investcompany.projects.view";
		
	}
	
	
}
