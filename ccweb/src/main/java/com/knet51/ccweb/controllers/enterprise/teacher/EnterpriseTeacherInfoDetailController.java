package com.knet51.ccweb.controllers.enterprise.teacher;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.services.EnterpriseTeacherService;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;


@Controller
public class EnterpriseTeacherInfoDetailController {
	private static Logger logger = 
			LoggerFactory.getLogger(EnterpriseTeacherInfoDetailController.class);
	@Autowired
	private EnterpriseTeacherService enterpriseTeacherService;
	public static final long MAX_COVER_SIZE_2M = 2*1024*1024;
	
	/**
	 * create a new teacher in enterprise 
	 * @param enterpriseTeacherInfoForm
	 * @param validResult
	 * @param session
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/enterprise/teacher/new/create",method=RequestMethod.POST)
	public String createTeacher(@Valid EnterpriseTeacherInfoForm enterpriseTeacherInfoForm,
			BindingResult validResult, HttpSession session,MultipartHttpServletRequest request,Model model,
			RedirectAttributes redirectAttributes) throws Exception{
		logger.info("#### Into enterprise teacher Add Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/enterprise/teacher/list";
		}else{
			List<MultipartFile> files = request.getFiles("coverFile");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			EnterpriseTeacher eTeacher = new EnterpriseTeacher();
			eTeacher.setContent(enterpriseTeacherInfoForm.getContent());
			eTeacher.setUser(userInfo.getUser());
			EnterpriseTeacher newTeacher = enterpriseTeacherService.createTeacher(eTeacher);
			for(int i=0;i<files.size();i++){
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					if(multipartFile.getSize()>MAX_COVER_SIZE_2M){
						redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
						return "redirect:/admin/enterprise/teacher/list";
					}else{
						logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
						String fileName = multipartFile.getOriginalFilename();
						String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
						String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+newTeacher.getId();
						logger.debug("Upload Path:"+path); 
						FileUtil.createRealPath(path, session);
						String previewFile = path+File.separator+"small"+"."+fileExtension;
						File saveDest = new File(path + File.separator + fileName);
						multipartFile.transferTo(saveDest);
						FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension);
						String savePath = FileUtil.getSavePath("course", userInfo.getId(), newTeacher.getId()+"", request)+"/small"+"."+fileExtension;
						newTeacher.setPhotourl(savePath);
					}
				}
			}
			enterpriseTeacherService.updateTeacher(newTeacher);
			return "redirect:/admin/enterprise/teacher/list";
		}
	}
	
	@RequestMapping(value="/admin/enterprise/teacher/edit/update")
	public String editTeacherInfo(@RequestParam("eteacherid") Long id,@Valid EnterpriseTeacherInfoForm enterpriseTeacherInfoForm,
			BindingResult validResult, HttpSession session,MultipartHttpServletRequest request,Model model,
			RedirectAttributes redirectAttributes) throws Exception{
		logger.info("#### Into enterprise teacher update Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/enterprise/teacher/edit/"+id;
		}else{
			List<MultipartFile> files = request.getFiles("coverFile");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			EnterpriseTeacher eTeacher = enterpriseTeacherService.findOneById(id);
			eTeacher.setContent(enterpriseTeacherInfoForm.getContent());
			for(int i=0;i<files.size();i++){
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					if(multipartFile.getSize()>MAX_COVER_SIZE_2M){
						redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
						return "redirect:/admin/enterprise/teacher/edit/"+id;
					}else{
						logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
						String fileName = multipartFile.getOriginalFilename();
						String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
						String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+eTeacher.getId();
						logger.debug("Upload Path:"+path); 
						FileUtil.createRealPath(path, session);
						String previewFile = path+File.separator+"small"+"."+fileExtension;
						File saveDest = new File(path + File.separator + fileName);
						multipartFile.transferTo(saveDest);
						FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension);
						String savePath = FileUtil.getSavePath("course", userInfo.getId(), eTeacher.getId()+"", request)+"/small"+"."+fileExtension;
						eTeacher.setPhotourl(savePath);
					}
				}
			}
			enterpriseTeacherService.updateTeacher(eTeacher);
			return "redirect:/admin/enterprise/teacher/list";
		}
	}
	
	@RequestMapping(value="/admin/enterprise/teacher/destory",method=RequestMethod.POST)
	public String destoryTeacher(@RequestParam("eteacherid") Long id,HttpSession session, Model m){
		enterpriseTeacherService.destoryTeacher(id);
		return "redirect:/admin/enterprise/teacher/list";
	}
	
}
