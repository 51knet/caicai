package com.knet51.ccweb.controllers.teacher.announcement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;

@Controller
public class TeacherAnnoDetailInfoController {
	
	private static Logger logger = 
			LoggerFactory.getLogger(TeacherAnnoDetailInfoController.class);
	public static final long MAX_FILE_SIZE_2M = 2*1024*1024;
	@Autowired
	private AnnouncementService annoService;
	@Autowired
	private UserService userService;
	/**
	 * create a new announcement
	 * @param annoDetailInfoForm
	 * @param request
	 * @param validResult
	 * @param session
	 * @param m
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/new", method = RequestMethod.POST)
	public String createNewAnno(@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm,MultipartHttpServletRequest request
			,BindingResult validResult, HttpSession session,Model m,RedirectAttributes redirectAttributes) throws Exception{
		logger.info("####  TeacherAnnoDetailController  ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		String role = userInfo.getRole();
		if(validResult.hasErrors()){
			logger.info("annoDetailInfoForm Validation Failed " + validResult);
			if(role.equals("teacher")){
				return "redirect:/admin/teacher/announcement/list";
			}else if(role.equals("enterprise")){
				return "redirect:/admin/enterprise/announcement/list";
			}else{
				return "redirect:/admin/user/announcement/list";
			}
		}else{
			logger.info("####  TeacherAnnoDetailController passed.  ####");
			List<MultipartFile> files = request.getFiles("coverFile");
			Long user_id = userInfo.getId();
			User user = userService.findOne(user_id);
			String title = annoDetailInfoForm.getTitle();
			String content = annoDetailInfoForm.getContent();
			Announcement announcement = new Announcement();
			announcement.setTitle(title);
			announcement.setContent(content);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			announcement.setDate(date); 
			announcement.setUser(user);
			Announcement ann = annoService.createAnnouncement(announcement);
			for(int i=0;i<files.size();i++){
				MultipartFile multipartFile = files.get(i);
				if(!files.get(i).isEmpty()){
					if(multipartFile.getSize()>MAX_FILE_SIZE_2M){
						redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
						if(role.equals("teacher")){
							return "redirect:/admin/teacher/announcement/list";
						}else if(role.equals("enterprise")){
							return "redirect:/admin/enterprise/announcement/list";
						}else{
							return "redirect:/admin/user/announcement/list";
						}
					}else{
						logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
						String fileName = multipartFile.getOriginalFilename();
						String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
						String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/announcement/"+ann.getId();
						logger.debug("Upload Path:"+path); 
						FileUtil.createRealPath(path, session);
						String previewFile = path+File.separator+"small"+"."+fileExtension;
						File saveDest = new File(path + File.separator + fileName);
						multipartFile.transferTo(saveDest);
						FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension);
						String savePath = FileUtil.getSavePath("announcement", userInfo.getId(), ann.getId()+"", request)+"/small"+"."+fileExtension;
						ann.setPhotourl(savePath);
					}
				}
			}
			annoService.updateAnnouncement(ann);
			if(role.equals("teacher")){
				return "redirect:/admin/teacher/announcement/list";
			}else if(role.equals("enterprise")){
				return "redirect:/admin/enterprise/announcement/list";
			}else{
				return "redirect:/admin/user/announcement/list";
			}
			
		}
	}
	/**
	 * destory a announcement
	 * @param anno_id
	 * @param session
	 * @param m
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/destory", method = RequestMethod.POST)
	public String teacherAnnoDele( @RequestParam("annoId") Long anno_id,HttpSession session, Model m){
		annoService.deleAnnouncementById(Long.valueOf(anno_id));
		return "redirect:/admin/teacher/announcement/list";
	}
	/**
	 * update a announcement
	 * @param anno_id
	 * @param annoDetailInfoForm
	 * @param validResult
	 * @param session
	 * @param m
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/announcement/edit/edit" , method = RequestMethod.POST)
	public String teacherAnnoUpdate(@RequestParam("id") Long anno_id,@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm,
			BindingResult validResult, HttpSession session,Model m){
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/announcement/edit/"+anno_id;
		}else{	
			String title = annoDetailInfoForm.getTitle();
			String content = annoDetailInfoForm.getContent();
			Announcement announcement = annoService.findOneById(anno_id);
			announcement.setTitle(title);
			announcement.setContent(content);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			announcement.setDate(date);  
			annoService.updateAnnouncement(announcement);
			return "redirect:/admin/teacher/announcement/list";
		}
	}
	
	@RequestMapping(value = "/admin/teacher/announcement/annoInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse noticeInfoFormAjaxJson(@Valid TeacherAnnoDetailInfoForm annoDetailInfoForm, 
									BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
}
