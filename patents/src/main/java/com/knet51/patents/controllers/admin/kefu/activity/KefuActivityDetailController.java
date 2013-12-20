package com.knet51.patents.controllers.admin.kefu.activity;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.knet51.ccweb.jpa.entities.Activity;
import com.knet51.patents.util.fileUpLoad.FileUtil;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.activity.ActivityService;

@Controller
public class KefuActivityDetailController {
	@Autowired
	private ActivityService activityService;
	private static final long MAX_FILE_SIZE_2M = 2*1024*1024;
	
	@RequestMapping(value="/admin/kefu/activity/new", method = RequestMethod.POST)
	public String createActivity(MultipartHttpServletRequest request,@Valid ActivityForm activityForm, BindingResult result,HttpSession session,
			RedirectAttributes redirectAttributes){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(result.hasErrors()){
			return "redirect:/admin/kefu/activity/create";
		}else{
			try{List<MultipartFile> files = request.getFiles("coverFile");
			Activity activity = new Activity();
			activity.setTitle(activityForm.getTitle());
			activity.setContent(activityForm.getContent());
			activity.setDate(new Date());
			activity.setUser(userInfo.getUser());
			Activity newActivity = activityService.create(activity);
			
			
				if(newActivity!= null){
					for(int i=0;i<files.size();i++){
						MultipartFile multipartFile = files.get(i);
						if(!files.get(i).isEmpty()){
							if(multipartFile.getSize()>MAX_FILE_SIZE_2M){
								redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
								return "redirect:/admin/kefu/activity/create";
							}else{	
								String fileName = multipartFile.getOriginalFilename();
								String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
								String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/activity/"+newActivity.getId();
								
								FileUtil.createRealPath(path, session);
								String previewFile = path+File.separator+"small"+"."+fileExtension;
								File saveDest = new File(path + File.separator + fileName);
								multipartFile.transferTo(saveDest);
								FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension,GlobalDefs.ACTIVITY_PHOTO_WIDTH,GlobalDefs.ACTIVITY_PHOTO_HEIGHT);
								String savePath = FileUtil.getSavePath("activity", userInfo.getId(), newActivity.getId()+"", request)+"/small"+"."+fileExtension;
								newActivity.setFilePath(savePath);
								activityService.update(newActivity);
							}
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return "redirect:/admin/kefu/activity/list";
		}
		
	}
	
	@RequestMapping(value="/admin/kefu/activity/edit", method = RequestMethod.POST)
	public String editActivity(MultipartHttpServletRequest request,@Valid ActivityForm activityForm, BindingResult result,HttpSession session,
			RedirectAttributes redirectAttributes,@RequestParam("activity_id") Long id){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(result.hasErrors()){
			return "redirect:/admin/kefu/activity/edit/"+id;
		}else{
			Activity activity = activityService.findOne(id);
			activity.setTitle(activityForm.getTitle());
			activity.setContent(activityForm.getContent());
			activity.setDate(new Date()); 
			List<MultipartFile> files = request.getFiles("coverFile");
			try{
				for(int i=0;i<files.size();i++){
					MultipartFile multipartFile = files.get(i);
					if(!files.get(i).isEmpty()){
						if(multipartFile.getSize()>MAX_FILE_SIZE_2M){
							redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
							return "redirect:/admin/kefu/activity/edit/"+id;
						}else{	
							String fileName = multipartFile.getOriginalFilename();
							String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
							String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/activity/"+activity.getId();
							
							FileUtil.createRealPath(path, session);
							String previewFile = path+File.separator+"small"+"."+fileExtension;
							File saveDest = new File(path + File.separator + fileName);
							multipartFile.transferTo(saveDest);
							FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension,GlobalDefs.ACTIVITY_PHOTO_WIDTH,GlobalDefs.ACTIVITY_PHOTO_HEIGHT);
							String savePath = FileUtil.getSavePath("activity", userInfo.getId(), activity.getId()+"", request)+"/small"+"."+fileExtension;
							activity.setFilePath(savePath);
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			activityService.update(activity);
			return "redirect:/admin/kefu/activity/list";
		}
		
	}
	
	@RequestMapping(value="/admin/kefu/activity/destory", method = RequestMethod.POST)
	public String destoryActivity(HttpSession session,@RequestParam("activity_id") Long id){
			activityService.delete(id);
			return "redirect:/admin/kefu/activity/list";

		
	}
}
