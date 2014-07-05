package com.graphene.web.controller.admin.announcement;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.announcement.Announcement;
import com.graphene.web.service.announcement.AnnouncementService;
import com.graphene.web.util.MyUtil;
import com.graphene.web.util.ajax.AjaxValidationEngine;
import com.graphene.web.util.ajax.ValidationResponse;
import com.graphene.web.util.fileUpload.FileUtil;



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
		Page<Announcement> page = annoService.findAnnoByUserAndType(pageNumber, pageSize, userInfo.getUser(), userRight);
		model.addAttribute("page", page);
		return "admin."+userRight+".announcement.list";
	}
	
	@RequestMapping(value="/admin/{userRight}/announcement/new" , method=RequestMethod.POST)
	public String addInvestcomAnno(@Valid AnnouncementForm annoForm, BindingResult result,HttpSession session,
			MultipartHttpServletRequest request,@PathVariable String userRight) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(result.hasErrors()){
			return "redirect:/admin/"+userRight+"/announcement/add";
		}else{
			Announcement anno = new Announcement();
			MyUtil.copyValidBeanToDestBean(annoForm, anno);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			anno.setDate(date);
			anno.setType(userRight);
			anno.setUser(userInfo.getUser());
			anno = annoService.createAnnouncement(anno);
			MultipartFile file = request.getFile("coverFile");
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/announcement/"+anno.getId();
				FileUtil.getInstance().createRealPath(path, session);
				String previewFile = path+File.separator+"small"+"."+fileExtension;
				File saveDest = new File(path + File.separator + fileName);
				file.transferTo(saveDest);
				FileUtil.getInstance().getPreviewImage(saveDest, new File(previewFile), fileExtension,GlobalDefs.ANNOUNCEMENT_PHOTO_WIDTH,GlobalDefs.ANNOUNCEMENT_PHOTO_HEIGHT);
				String savePath = FileUtil.getInstance().getSavePath("announcement", userInfo.getId(), anno.getId()+"", request)+"/small"+"."+fileExtension;
				anno.setFilePath(savePath);
				annoService.updateAnnouncement(anno);
			}
			return "redirect:/admin/"+userRight+"/announcement/list";
		}
	}
	
	@RequestMapping(value="/admin/{userRight}/announcement/edit/{id}")
	public String editInvestcomAnnoPage(@PathVariable Long id,HttpSession session,Model model,@PathVariable String userRight){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Announcement anno = annoService.findOne(id);
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
		Announcement anno = annoService.findOne(anno_id);
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
		Announcement anno = annoService.findOne(anno_id);
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
	@RequestMapping(value ="/admin/announcement/editAnnoInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse editAnnouncementInfoFormAjaxJson(@Valid AnnouncementForm announcementForm , BindingResult result) {
		return AjaxValidationEngine.process(result);
	}

	
}
