package com.knet51.ccweb.controllers.teacher.resource;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.teacher.TeacherWorkExpInfoForm;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.jpa.services.ResourceTypeService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;
import com.knet51.ccweb.util.fileUpLoad.SavePathUtil;

@Controller
public class TeacherResouDetailInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherResouInfoPageController.class);
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResourceTypeService resourceTypeService;
	@Autowired 
	private CourseResourceService courseResourceService;
	
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/new/create",method=RequestMethod.POST)
	public String teacherResouInfo(HttpSession session,Model model,@RequestParam("desc") String desc,
			@RequestParam("type") Long value, MultipartHttpServletRequest request) throws Exception{
		logger.info("#####Into TeacherResouInfoAddPageController#####");
		List<MultipartFile> files = request.getFiles("myFiles");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				MultipartFile multipartFile = files.get(i);
				CourseResource resource = new CourseResource();
				logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 
				String fileName = files.get(i).getOriginalFilename();
				String name = fileName.substring(0, fileName.indexOf("."));
				ResourceType resourceType = resourceTypeService.findOneById(value); 
				//String realPath = FileUtil.getPath("upload", userInfo.getId(), resourceType.getTypeName(),session);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/upload/"+resourceType.getTypeName();
				FileUtil.createRealPath(path, session);
				File saveDest = new File(path + File.separator + fileName);
				multipartFile.transferTo(saveDest);
				resource.setResourceType(resourceType);
				resource.setResourceDesc(desc);
				resource.setFileName(name);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = format.format(new Date());
				resource.setDate(date);
				resource.setSaveName(fileName);
				String savePath = path+File.separator+fileName;
				resource.setSavePath(savePath);
				resource.setStatus(GlobalDefs.STATUS_RESOURCE);
				resource.setUser(user);
				courseResourceService.createCourseResource(resource);
			}
		}
		return "redirect:/admin/teacher/resource/list";
	}

//	@Override
//	public ModelAndView resolveException(HttpServletRequest request,
//			HttpServletResponse response, Object obj, Exception e) {
//		// TODO Auto-generated method stub
//		Map<Object, Object> model = new HashMap<Object, Object>(); 
//		if (e instanceof MaxUploadSizeExceededException){ 
//				model.put("errors", "File size should be less then "+ 
//						((MaxUploadSizeExceededException)e).getMaxUploadSize()+" byte."); 
//		} else{ 
//				model.put("errors", "Unexpected error: " + e.getMessage()); 
//		} 
//		return new ModelAndView("teacherResouAddDetail", (Map) model); 
//		
//	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/destory",method=RequestMethod.POST)
	public String teacherResouDele(HttpSession session,Model model, @RequestParam("resourceId")Long resource_id) throws Exception{
		logger.info("#####Into TeacherResouInfoDelePageController#####");
		CourseResource resource = resourceService.findOneById(resource_id);
		resourceService.delete(resource, GlobalDefs.STATUS_RESOURCE_DESTORY);
		return "redirect:/admin/teacher/resource/list";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/type/new",  method = RequestMethod.POST)
	public String teacherResouTypeAdd(@Valid TeacherResouTypeInfoForm teacherResouTypeInfo,
			BindingResult validResult, HttpSession session,Model model){
		String typeName = teacherResouTypeInfo.getTypeName();
		if (validResult.hasErrors()) {
			 logger.info("detailInfoForm Validation Failed " + validResult);
			 return "redirect:/admin/teacher/resource/new";
		} else {
			ResourceType resourceType = new ResourceType();
			resourceType.setTypeName(typeName);
			resourceTypeService.save(resourceType);
			return "redirect:/admin/teacher/resource/new";
		}
	}
	@RequestMapping(value = "/admin/teacher/resource/resourceTypeAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse resourceTypeFormAjaxJson(@Valid  TeacherResouTypeInfoForm teacherResouTypeInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/type/destory/{resourceType_id}")
	public String teacherResouTypeDele(HttpSession session,Model model,@PathVariable Long resourceType_id) throws Exception{
		logger.info("#####Into TeacherResouTypeDelePageController#####");
		resourceTypeService.deleteById(resourceType_id);
		return "redirect:/admin/teacher/resource/type/list";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/type/new/create",  method = RequestMethod.POST)
	public String teacherResouTypeAddInfo(@Valid TeacherResouTypeInfoForm teacherResouTypeInfo,
			BindingResult validResult, HttpSession session,Model model){
		String typeName = teacherResouTypeInfo.getTypeName();
		if (validResult.hasErrors()) {
			 logger.info("detailInfoForm Validation Failed " + validResult);
			 return "redirect:/admin/teacher/resource/type/list";
		} else {
			ResourceType resourceType = new ResourceType();
			resourceType.setTypeName(typeName);
			resourceTypeService.save(resourceType);
			return "redirect:/admin/teacher/resource/type/list";
		}
	}
	
	@RequestMapping(value="/resource/download/{resource_id}")
	public String resourceDownLoad(@PathVariable Long resource_id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("-------Into resource DownLoad controller------");
		CourseResource resource = resourceService.findOneById(resource_id);
		String savePath = resource.getSavePath();
		String fileName = resource.getSaveName();
		FileUtil.downLoad(request, response, savePath, fileName);
		return null;
	}
	

}
