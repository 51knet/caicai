package com.knet51.ccweb.controllers.teacher.course;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import org.springframework.web.multipart.MultipartRequest;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.teacher.TeacherPersonalInfoForm;
import com.knet51.ccweb.controllers.teacher.TeacherWorkExpInfoForm;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;


@Controller
public class TeacherCourseInfoDetailController {
	private static Logger logger = 
			LoggerFactory.getLogger(TeacherCourseInfoDetailController.class);
	
	@Autowired
	private TeacherCourseService courseService;
	
	@Autowired
	private CourseResourceService courseResourceService; 
	
	@Autowired
	private TeacherService teacherService;
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/create",method=RequestMethod.POST)
	public String TeacherCourseAddInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session,MultipartHttpServletRequest request) throws Exception{
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/course/list";
		}else{
			List<MultipartFile> files = request.getFiles("coverFile");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Teacher teacher = teacherService.findOne(userInfo.getId());
			TeacherCourse course = new TeacherCourse();
			String courseName = courseInfoForm.getCourseName();
			String courseDesc = courseInfoForm.getCourseDesc();
			course.setCourseName(courseName);
			course.setCourseDesc(courseDesc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			course.setCourseDate(date);
			course.setTeacher(teacher);
			course.setCourseType(courseInfoForm.getCourseType());
			for(int i=0;i<files.size();i++){
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
					String fileName = multipartFile.getOriginalFilename();
					String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
					String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+courseName;
					logger.debug("Upload Path:"+path); 
					FileUtil.createRealPath(path, session);
					String previewFile = path+File.separator+"small"+"."+fileExtension;
					File saveDest = new File(path + File.separator + fileName);
					multipartFile.transferTo(saveDest);
					FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension);
					String savePath = FileUtil.getSavePath("course", userInfo.getId(), courseName, request)+File.separator+"small"+"."+fileExtension;
					course.setCourseCover(savePath);
				}
			}
			
			courseService.createTeacherCourse(course);
			return "redirect:/admin/teacher/course/list";
		}
	
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/edit",method=RequestMethod.POST)
	public String TeacherCourseUpdateInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session,@RequestParam("id") Long course_id){
		
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/course/edit/"+course_id;
		}else{
			TeacherCourse course = courseService.findOneById(course_id);
			String courseName = courseInfoForm.getCourseName();
			String courseDesc = courseInfoForm.getCourseDesc();
			course.setCourseName(courseName);
			course.setCourseDesc(courseDesc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			course.setCourseDate(date);
			courseService.updateTeacherCourse(course);
			return "redirect:/admin/teacher/course/list";
		}
	
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/destory/{course_id}")
	public String TeacherCourseDele( HttpSession session,@PathVariable Long course_id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
			//courseService.deleTeacherCourse(course_id);
			TeacherCourse course = courseService.findOneById(course_id);
			course.setPublish(0);
			courseService.updateTeacherCourse(course);
			return "redirect:/admin/teacher/course/list";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/deleted/{course_id}")
	public String courseRecoverPublish( HttpSession session,@PathVariable Long course_id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
			courseService.deleTeacherCourse(course_id);
			return "redirect:/admin/teacher/course/list";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/recover/{course_id}")
	public String courseRecoverUnpublish( HttpSession session,@PathVariable Long course_id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
			//courseService.deleTeacherCourse(course_id);
			TeacherCourse course = courseService.findOneById(course_id);
			course.setPublish(1);
			courseService.updateTeacherCourse(course);
			return "redirect:/admin/teacher/course/list";
	}
	
	
	@Transactional
	@RequestMapping(value="/admin/teacher/{course_id}/resource/create",method=RequestMethod.POST)
	public String TeacherCourseResourceAdd(HttpSession session,Model model,
			MultipartHttpServletRequest request,@PathVariable Long course_id) throws  Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<MultipartFile> files = request.getFiles("file");
		String resourceDesc = request.getParameter("resourceDesc");
		String resourceOrder = request.getParameter("resourceOrder");
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				MultipartFile multipartFile = files.get(i);
				CourseResource resource = new CourseResource();
				logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 	
				String fileName = multipartFile.getOriginalFilename();
				String name = fileName.substring(0, fileName.indexOf("."));
				resource.setFileName(name);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = format.format(new Date());
				resource.setDate(date);
				
				TeacherCourse teacherCourse = courseService.findOneById(course_id);
				//String realPath = FileUtil.getPath("courseResource", userInfo.getId(), teacherCourse.getCourseName(), session);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+teacherCourse.getCourseName()+"/"+resourceOrder;
				FileUtil.createRealPath(path, session);
				File saveDest = new File(path + File.separator + fileName);
				multipartFile.transferTo(saveDest);
			//	String saveName = FileUtil.saveFile(files.get(i).getInputStream(), fileName, path);
				String savePath = path+File.separator+fileName;
				resource.setSavePath(savePath);
				resource.setSaveName(fileName);
				resource.setResourceDesc(resourceDesc);
				resource.setResourceOrder(resourceOrder);
				resource.setCourse_id(course_id);
				courseResourceService.createCourseResource(resource);
			}
		}
		return "redirect:/admin/teacher/course/view/"+course_id;
	}
	
	
	@RequestMapping(value="/course/resource/download/{resource_id}")
	public String resourceDownLoad(@PathVariable Long resource_id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("-------Into resource DownLoad controller------");
		CourseResource resource = courseResourceService.findOneById(resource_id);
		String savePath = resource.getSavePath();
		String fileName = resource.getSaveName();
		FileUtil.downLoad(request, response, savePath, fileName);
		return null;
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/{course_id}/resource/destory/{resource_id}")
	public String courseResourceDele( HttpSession session,@PathVariable Long resource_id,@PathVariable Long course_id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
			courseResourceService.deleCourseResource(resource_id);
			return "redirect:/admin/teacher/course/view/"+course_id;
	}
	/**
	 * 修改课程
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/modifycourse")
	public String modifyCreateTeacherCourse(HttpSession session,@PathVariable Long id,Model model,HttpServletRequest request){
		TeacherCourse course=courseService.findOneById(id);
		List<CourseResource> listResource = courseResourceService.getResourceByCourseId(id);
		CourseResource courses = new CourseResource();
		Map<String, CourseResource> courseMap = new TreeMap<String, CourseResource>();
		String resourceOrder = null;
		for (CourseResource courseResource : listResource) {
			resourceOrder = courseResource.getResourceOrder();
			courses = courseResourceService
					.getResourceByResourceOrderAndCourseId(resourceOrder,id);
			courseMap.put(resourceOrder, courses);
		}
		model.addAttribute("resourceCount", listResource.size());
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.modifycourse";
	}
	/**
	 * 基本信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/basicinfo")
	public String basicMessage(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.basicinfo";
	}
	/**
	 * 修改基本信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/basicinfomodify")
	public String modifyBasicMessage(HttpSession session,@PathVariable Long id,Model model,HttpServletRequest request,@Valid TeacherCourseInfoForm teacherCourseInfoForm,BindingResult validResult){
		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/course/edit/{id}/basicinfo";
		}else{
		TeacherCourse course=courseService.findOneById(id);
		String courseName=teacherCourseInfoForm.getCourseName();
		String courseType=teacherCourseInfoForm.getCourseType();
		String courseDesc=teacherCourseInfoForm.getCourseDesc();
		if(courseName!=null||courseType!=null||courseDesc!=null){
			course.setCourseName(courseName);
			course.setCourseType(courseType);
			course.setCourseDesc(courseDesc); 
			courseService.updateTeacherCourse(course);
		}
		model.addAttribute("course", course);
	}
		return "redirect:/admin/teacher/course/edit/"+id+"/basicinfo";
	}
	/***
	 * 详细信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/detailinfo")
	public String detailMessage(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.detailinfo";
	}
	/***
	 * 修改详细信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/detailinfomodify")
	public String modifyDetailMessage(HttpSession session,@PathVariable Long id,Model model,HttpServletRequest request){
		String character=request.getParameter("courseCharacter");
		String targetPerson=request.getParameter("targetPerson");
		TeacherCourse course=courseService.findOneById(id);
		course.setCourseCharacter(character);
		course.setTargetPerson(targetPerson);
		model.addAttribute("course", course);
		return "redirect:/admin/teacher/course/edit/"+id+"/detailinfo";
	}
	/***
	 * 封面
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/cover")
	public String CreateCover(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.cover";
	}
	/***
	 * 修改封面
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/moidfycover",method=RequestMethod.POST)
	public String modifyCreateCover(HttpSession session,@PathVariable Long id,MultipartHttpServletRequest request,Model model) throws Exception{
			List<MultipartFile> files = request.getFiles("coverFile");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		TeacherCourse teacherCourse=courseService.findOneById(id);
		String courseName = teacherCourse.getCourseName();
		for(int i=0;i<files.size();i++){
			MultipartFile multipartFile = files.get(i);
			if(!files.get(i).isEmpty()){
				//logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
				String fileName = multipartFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+courseName;
				logger.debug("Upload Path:"+path); 
				FileUtil.createRealPath(path, session);
				String previewFile = path+File.separator+"small"+"."+fileExtension;
				File saveDest = new File(path + File.separator + fileName);
				multipartFile.transferTo(saveDest);
				FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension);
				String savePath = FileUtil.getSavePath("course", userInfo.getId(), courseName, request)+File.separator+"small"+"."+fileExtension;
				teacherCourse.setCourseCover(savePath);
			}      
		}
		TeacherCourse course = courseService.updateTeacherCourse(teacherCourse);
		model.addAttribute("course", course);
		return "redirect:/admin/teacher/course/edit/"+id+"/cover";
		}
	/**
	 * 修改视频
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/watchvideo")
	public String modifyWatchVideo(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.watchvideo";
	}
	/**
	 * 权限设置
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/powerprice")
	public String powerPrice(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.powerprice";
	}
	/**
	 * 修改权限设置
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/powerpricemodify")
	public String modifyPowerPrice(HttpSession session,@PathVariable Long id,Model model,HttpServletRequest request){
		Integer status=Integer.parseInt(request.getParameter("status"));
		String pwd=request.getParameter("pwd");
		TeacherCourse course=courseService.findOneById(id);
		course.setStatus(status);
		course.setPwd(pwd.trim());
		model.addAttribute("course", course);
		return "redirect:/admin/teacher/course/edit/"+id+"/powerprice";
	}
	/**
	 * 取消课程
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/deletecourse")
	public String modifyDeleteMessage(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.deletecourse";
	}
	/**
	 * 删除课程
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/deletecoursemodify")
	public String deleteMessage(HttpSession session,@PathVariable Long id,Model model){
			courseService.deleTeacherCourse(id);
		return "redirect:/admin/teacher/course/list";
	}
	
}
