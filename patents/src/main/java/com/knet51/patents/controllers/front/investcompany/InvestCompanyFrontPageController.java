package com.knet51.patents.controllers.front.investcompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.incubator.IncubatInfor;
import com.knet51.ccweb.jpa.entities.investcompany.InvestComInfor;
import com.knet51.ccweb.jpa.entities.investcompany.InvestSuccessCase;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.announcement.AnnouncementService;
import com.knet51.patents.jpa.services.investcompany.InvestComInforService;
import com.knet51.patents.jpa.services.investcompany.InvestSuccessCaseService;

@Controller
@RequestMapping(value="/investcompany/{id}")
public class InvestCompanyFrontPageController {
	@Autowired
	private UserService userService;
	@Autowired
	private InvestComInforService comInforService;
	@Autowired
	private InvestSuccessCaseService successCaseService;
	@Autowired
	private AnnouncementService annoService;
	
	@RequestMapping(value="")
	public String showInvestComHomePage(@PathVariable Long id,Model model){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("investComUserInfo", userInfo);
		
		InvestComInfor investComInfo = comInforService.findByUser(user);
		model.addAttribute("investComInfo", investComInfo);
		
		Page<Announcement> annoPage = annoService.findAnnoByUserAndCode(0, 5, user, GlobalDefs.investcompany);
		model.addAttribute("annoPage", annoPage);
		
		Page<InvestSuccessCase> casePage = successCaseService.findSuccessCaseByUser(user, 0, 5);
		model.addAttribute("casePage", casePage);
		return "investcompany.basic";
	}
	
	@RequestMapping(value="/about")
	public String showInvestComInfor(Model model,@PathVariable Long id){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("investComUserInfo", userInfo);
		
		InvestComInfor investComInfo = comInforService.findByUser(user);
		model.addAttribute("investComInfo", investComInfo);
		return "investcompany.about"; 
	}
	
	@RequestMapping(value="/announcement/list")
	public String showAnnouncementList(@PathVariable Long id,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize,Model model){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("investComUserInfo", userInfo);
		
		Page<Announcement> page = annoService.findAnnoByUserAndCode(pageNumber, pageSize, user, GlobalDefs.investcompany);
		model.addAttribute("page", page);
		return "investcompany.announcement.list";
	}
	
	@RequestMapping(value="/announcement/view/{anno_id}")
	public String showAnnouncementDetail(@PathVariable Long id,@PathVariable Long anno_id,Model model){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("investComUserInfo", userInfo);
			
		Announcement anno = annoService.findOneById(anno_id);
		model.addAttribute("announcement", anno);
		return "investcompany.announcement.view";
	}
	@RequestMapping(value="/successcase/list")
	public String showSuccesscaseList(@PathVariable Long id,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize,Model model){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("investComUserInfo", userInfo);
		
		Page<InvestSuccessCase> page = successCaseService.findSuccessCaseByUser(user, pageNumber, pageSize);
		model.addAttribute("page", page);
		return "investcompany.successcase.list";
	}
	
	@RequestMapping(value="/successcase/view/{case_id}")
	public String showSuccesscaseDetail(@PathVariable Long id,@PathVariable Long case_id,Model model){
		User user = userService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		model.addAttribute("investComUserInfo", userInfo);
		
		InvestSuccessCase successCase = successCaseService.findOne(case_id);
		model.addAttribute("successCase", successCase);
		return "investcompany.successcase.view";
	}
	
	
	
}
