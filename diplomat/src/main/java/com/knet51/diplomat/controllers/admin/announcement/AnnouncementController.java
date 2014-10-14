package com.knet51.diplomat.controllers.admin.announcement;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.knet51.ccweb.jpa.entities.AnnoPhoto;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.diplomat.beans.UserInfo;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.announcement.AnnouncementService;
import com.knet51.diplomat.util.MyUtil;
import com.knet51.diplomat.util.ajax.AjaxValidationEngine;
import com.knet51.diplomat.util.ajax.ValidationResponse;

@Controller
public class AnnouncementController {
	
	@Autowired
	private AnnouncementService annoService;
	
	/* investcompany announcement */
	@RequestMapping(value="/admin/{userRight}/announcement/add")
	public String showInvestComNewAnnoPage(@PathVariable String userRight){
		return "admin."+userRight+".announcement.add";
	}
	
	@RequestMapping(value="/admin/{userRight}/announcement/list")
	public String showInvestComAnnoListPage(@PathVariable String userRight,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize,HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO );
		Integer code = userRight.equals("investcompany")? GlobalDefs.investcompany : GlobalDefs.incubator;
		Page<Announcement> page = annoService.findAnnoByUserAndCode(pageNumber, pageSize, userInfo.getUser(), code);
		model.addAttribute("page", page);
		return "admin."+userRight+".announcement.list";
	}
	
	@RequestMapping(value="/admin/{userRight}/announcement/new" , method=RequestMethod.POST)
	public String addInvestcomAnno(@Valid AnnouncementForm annoForm, BindingResult result,HttpSession session,@PathVariable String userRight){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(result.hasErrors()){
			return "redirect:/admin/"+userRight+"/announcement/add";
		}else{
			Announcement anno = new Announcement();
			MyUtil.copyValidBeanToDestBean(annoForm, anno);
			Integer code = userRight.equals("investcompany")? GlobalDefs.investcompany : GlobalDefs.incubator;
			anno.setCode(code);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			anno.setDate(date);
			anno.setUser(userInfo.getUser());
			annoService.createAnnouncement(anno);
			return "redirect:/admin/"+userRight+"/announcement/list";
		}
	}
	
	@RequestMapping(value="/admin/{userRight}/announcement/edit/{id}")
	public String editInvestcomAnnoPage(@PathVariable Long id,HttpSession session,Model model,@PathVariable String userRight){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Announcement anno = annoService.findOneById(id);
		if(!anno.getUser().getId().equals(userInfo.getId())){
			return "redirect:/";
		}
		model.addAttribute("anno", anno);
		return "admin."+userRight+".announcement.edit";
	}
	
	@RequestMapping(value="/admin/{userRight}/announcement/edit/edit" , method=RequestMethod.POST)
	public String editInvestcomAnno(@Valid AnnouncementForm annoForm, BindingResult result,HttpSession session,
			@RequestParam("anno_id") Long anno_id,@PathVariable String userRight){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Announcement anno = annoService.findOneById(anno_id);
		if(!anno.getUser().getId().equals(userInfo.getId())){
			return "redirect:/";
		}
		if(result.hasErrors()){
			return "redirect:/admin/"+userRight+"/announcement/edit/"+anno_id;
		}else{
			MyUtil.copyValidBeanToDestBean(annoForm, anno);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			anno.setDate(date);
			annoService.updateAnnouncement(anno);
			return "redirect:/admin/"+userRight+"/announcement/list";
		}
	}
	
	@RequestMapping(value="/admin/{userRight}/announcement/destory", method = RequestMethod.POST)
	public String teacherAnnoDele( @RequestParam("anno_id") Long anno_id,HttpSession session, Model m,@PathVariable String userRight){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Announcement anno = annoService.findOneById(anno_id);
		if(!anno.getUser().getId().equals(userInfo.getId())){
			return "redirect:/";
		}
		annoService.deleAnnouncementById(Long.valueOf(anno_id));
		return "redirect:/admin/"+userRight+"/announcement/list";
	}
	
	@RequestMapping(value ="/admin/announcement/annoInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse announcementInfoFormAjaxJson(@Valid AnnouncementForm announcementForm , BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
}
