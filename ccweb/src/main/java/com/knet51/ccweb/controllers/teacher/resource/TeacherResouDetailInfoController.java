package com.knet51.ccweb.controllers.teacher.resource;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.jpa.services.ResourceTypeService;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;

@Controller
public class TeacherResouDetailInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherResouInfoPageController.class);
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/addInfo",method=RequestMethod.POST)
	public String teacherResouInfo(HttpSession session,Model model,@RequestParam("desc") String desc,
			@RequestParam("type") Long value, MultipartHttpServletRequest request) throws Exception{
		logger.info("#####Into TeacherResouInfoAddPageController#####");
		List<MultipartFile> files = request.getFiles("myFiles");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		User user = userInfo.getUser();
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				Resource resource = new Resource();
				//ResourceType resourceType = new ResourceType();
				logger.info("上传文件名称"+files.get(i).getOriginalFilename()); 
				String fileName = files.get(i).getOriginalFilename();
				String realPath = session.getServletContext().getRealPath("/WEB-INF/temp/");
				resource.setDescription(desc);
				resource.setName(fileName);
				ResourceType resourceType = resourceTypeService.findOneById(value); 
				resource.setResourceType(resourceType);
				resource.setStatus(1);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = format.format(new Date());
				resource.setDate(date);
				String savePath = FileUtil.saveFile(files.get(i).getInputStream(), fileName, realPath);
				resource.setSavePath(savePath);
				resourceService.create(resource, user);
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
	@RequestMapping(value="/admin/teacher/resource/dele")
	public String teacherResouDele(HttpSession session,Model model,@RequestParam("id") Long id) throws Exception{
		logger.info("#####Into TeacherResouInfoDelePageController#####");
//		Resource resource = resourceService.findOneById(id);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date = format.format(new Date());
//		resource.setDate(date);
//		resourceService.delete(resource, 2);
		resourceService.deleteResource(id);
		return "redirect:/admin/teacher/resource/list";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/addtype",  method = RequestMethod.POST)
	public String teacherResouTypeAdd(@Valid TeacherResouTypeInfoForm teacherResouTypeInfo,
			BindingResult validResult, HttpSession session,Model model){
		String typeName = teacherResouTypeInfo.getTypeName();
		if (validResult.hasErrors()) {
			 logger.info("detailInfoForm Validation Failed " + validResult);
			 return "/admin/teacher/resource/add";
		} else {
			ResourceType resourceType = new ResourceType();
			resourceType.setTypeName(typeName);
			resourceTypeService.save(resourceType);
			return "redirect:/admin/teacher/resource/add";
		}
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/type/dele")
	public String teacherResouTypeDele(HttpSession session,Model model,@RequestParam("id") Long id) throws Exception{
		logger.info("#####Into TeacherResouTypeDelePageController#####");
		resourceTypeService.deleteById(id);
		return "redirect:/admin/teacher/resource/type";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/resource/type/addInfo",  method = RequestMethod.POST)
	public String teacherResouTypeAddInfo(@Valid TeacherResouTypeInfoForm teacherResouTypeInfo,
			BindingResult validResult, HttpSession session,Model model){
		String typeName = teacherResouTypeInfo.getTypeName();
		if (validResult.hasErrors()) {
			 logger.info("detailInfoForm Validation Failed " + validResult);
			 return "/admin/teacher/resource/type/add";
		} else {
			ResourceType resourceType = new ResourceType();
			resourceType.setTypeName(typeName);
			resourceTypeService.save(resourceType);
			return "redirect:/admin/teacher/resource/type";
		}
	}
	

}
