package com.knet51.ccweb.controllers.admin.teacher.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.entities.timeline.Trends;
import com.knet51.ccweb.jpa.services.TrendsService;
import com.knet51.ccweb.jpa.services.course.CourseResourceService;
import com.knet51.ccweb.jpa.services.resources.ResourceService;
import com.knet51.ccweb.jpa.services.resources.ResourceTypeService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;
import com.knet51.ccweb.util.fileUpLoad.FTPUtil;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;

@Controller
public class TeacherResouDetailInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherResouInfoPageController.class);
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResourceTypeService resourceTypeService;
	@Autowired 
	private CourseResourceService courseResourceService;
	@Autowired
	private TrendsService trendsService;
	
	/**
	 *  create a new resource
	 * @param session
	 * @param model
	 * @param desc
	 * @param value
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/admin/resource/new/create",method=RequestMethod.POST)
	public String teacherResouInfo(HttpSession session,Model model,@RequestParam("desc") String desc,
			@RequestParam("type") Long value, MultipartHttpServletRequest request) throws Exception{
		logger.info("#####Into TeacherResouInfoAddPageController#####"+session.getId());
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<MultipartFile> files = request.getFiles("myFiles");

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
				//String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/upload/"+resourceType.getTypeName();
				String path = "/resources/attached/"+userInfo.getId()+"/upload/"+resourceType.getTypeName();
				//FileUtil.createRealPath(path, session);
				//File saveDest = new File(path + File.separator + fileName);
				InputStream fileInput = multipartFile.getInputStream();
				boolean flag =  FTPUtil.getInstance().uploadFile(path, fileName, fileInput);
				
				if(flag){
					resource.setResourceType(resourceType);
					resource.setResourceDesc(desc);
					resource.setFileName(name);
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String date = format.format(new Date());
					resource.setDate(date);
					resource.setSaveName(fileName);
					String savePath = path+"/"+fileName;
					resource.setSavePath(savePath);
					resource.setStatus(GlobalDefs.STATUS_RESOURCE);
					resource.setUser(user);
					CourseResource newResource =  courseResourceService.createCourseResource(resource);
					
					Trends trends = new Trends();
					trends.setUser(userInfo.getUser());
					
					trends.setTitle(newResource.getFileName());
					trends.setPublishDate(new Date());
					trends.setItemId(newResource.getId());
					trends.setVariety("resource");
					trendsService.createTrends(trends);
				}
				
				
			}
		}
		return "redirect:/admin/resource/list";
	}
	
	@RequestMapping(value="/admin/resource/new/new",method=RequestMethod.POST)
	public void newResouInfo(HttpSession session,Model model,HttpServletRequest httpRequest,HttpServletResponse response) throws Exception{
		logger.info("#####Into TeacherResouInfoAddPageController#####");
		PrintWriter out = response.getWriter();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpRequest;
		//UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		String desc = request.getParameter("rdesc");
		String id = request.getParameter("sessionid");
		logger.info("============="+desc+"====="+id);
		Map<String, MultipartFile> fileMap = request.getFileMap();
		for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
			MultipartFile file = entry.getValue();
			String fileName = file.getOriginalFilename();
			file.transferTo(new File("D:/ajaxfile/"+fileName));
			System.out.println("-------"+fileName);
		}
		out.print(desc);
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="/resource/processbar",method=RequestMethod.POST)
	public void process(HttpServletResponse response, HttpSession session) throws IOException{
		logger.info("======================+processBar");
		CommonsMultipartFile file = (CommonsMultipartFile) session.getAttribute("file");
		long processPercent = 0;
		long totalSize = file.getSize();
		long readSize = file.getFileItem().getSize();
		logger.info("totalSize==="+totalSize+"readSize====="+readSize);
		if(totalSize != 0){
			processPercent =  Math.round(readSize / totalSize) * 100;
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(processPercent);
		writer.flush();
		writer.close();
	}

	/**
	 * destory a resource
	 * @param session
	 * @param model
	 * @param resource_id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/admin/resource/destory",method=RequestMethod.POST)
	public String teacherResouDele(HttpSession session,Model model, @RequestParam("resourceId")Long resource_id) throws Exception{
		logger.info("#####Into TeacherResouInfoDelePageController#####");
		CourseResource resource = resourceService.findOneById(resource_id);
		resourceService.delete(resource, GlobalDefs.STATUS_RESOURCE_DESTORY);
		return "redirect:/admin/resource/list";
	}
	

	@RequestMapping(value = "/admin/resource/resourceTypeAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse resourceTypeFormAjaxJson(@Valid  TeacherResouTypeInfoForm teacherResouTypeInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@Transactional
	@RequestMapping(value="/admin/resource/type/destory/{resourceType_id}")
	public String teacherResouTypeDele(HttpSession session,Model model,@PathVariable Long resourceType_id) throws Exception{
		logger.info("#####Into TeacherResouTypeDelePageController#####");
		resourceTypeService.deleteById(resourceType_id);
		return "redirect:/admin/resource/type/list";
	}
	
	/**
	 * create a new resource type
	 * @param teacherResouTypeInfo
	 * @param validResult
	 * @param session
	 * @param model
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/resource/type/new/create",  method = RequestMethod.POST)
	public String teacherResouTypeAddInfo(@Valid TeacherResouTypeInfoForm teacherResouTypeInfo,
			BindingResult validResult, HttpSession session,Model model){
		String typeName = teacherResouTypeInfo.getTypeName();
		if (validResult.hasErrors()) {
			 logger.info("detailInfoForm Validation Failed " + validResult);
			 return "redirect:/admin/resource/type/list";
		} else {
			ResourceType resourceType = new ResourceType();
			resourceType.setTypeName(typeName);
			resourceType.setTypeStatus(GlobalDefs.STATUS_RESOURCETYPE);
			resourceTypeService.save(resourceType);
			return "redirect:/admin/resource/type/list";
		}
	}
	/**
	 * download a resource
	 * @param resource_id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/resource/download/{resource_id}")
	public String resourceDownLoad(@PathVariable Long resource_id,HttpServletRequest request,HttpServletResponse response) {
		logger.info("-------Into resource DownLoad controller------");
		try {

			CourseResource resource = resourceService.findOneById(resource_id);
			String savePath = resource.getSavePath();
			//String fullPath = FTPUtil.getInstance().getDownloadFileFullPath(savePath);
			String fileName = resource.getSaveName();
			//FileUtil.downLoad(request, response, fullPath,fileName);
			FTPUtil.getInstance().ftpDownLoad( response, savePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
