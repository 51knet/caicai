package com.knet51.ccweb.controllers.teacher.course;
import java.io.File;
import java.io.IOException;
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
				if(!files.get(i).isEmpty()){
					logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 
					String fileName = files.get(i).getOriginalFilename();
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					//String realPath = FileUtil.getPath("course", userInfo.getId(), courseName,  session);
					String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+courseName;
					FileUtil.createRealPath(path, session);
					//logger.info("+++++++++++++"+realPath);
					String previewFile = path+"/small"+fileType;
					String saveName = FileUtil.saveFile(files.get(i).getInputStream(), fileName, path);
					FileUtil.getPreviewImage(new File(path+"/"+saveName), new File(previewFile), fileType.substring(1));
					String savePath = FileUtil.getSavePath("course", userInfo.getId(), courseName, request)+"/"+"small"+fileType;
					//String savePath = request.getContextPath()+"/course/"+userInfo.getId()+"/"+courseName+"/"+saveName;
					course.setCourseCover(savePath);
				}
			}
			
			courseService.createTeacherCourse(course);
			return "redirect:/admin/teacher/course/list";
		}
	
	}
	
	
	
	@RequestMapping(value = "/admin/teacher/course/courserInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse courseInfoFormAjaxJson(@Valid TeacherCourseInfoForm teacherCourseInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
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
			courseService.deleTeacherCourse(course_id);
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
				CourseResource resource = new CourseResource();
				logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 
				
				String fileName = files.get(i).getOriginalFilename();
				String name = fileName.substring(0, fileName.indexOf("."));
				resource.setFileName(name);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = format.format(new Date());
				resource.setDate(date);
				
				TeacherCourse teacherCourse = courseService.findOneById(course_id);
				//String realPath = FileUtil.getPath("courseResource", userInfo.getId(), teacherCourse.getCourseName(), session);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+teacherCourse.getCourseName()+"/"+resourceOrder;
				FileUtil.createRealPath(path, session);
				String saveName = FileUtil.saveFile(files.get(i).getInputStream(), fileName, path);
				String savePath = path+"\\"+saveName;
				resource.setSavePath(savePath);
				resource.setSaveName(saveName);
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
	public String modifyCreateTeacherCourse(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.modifycourse";
	}
	/**
	 * 修改基本信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/basicinfo")
	public String modifyBasicMessage(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.basicinfo";
	}
	/***
	 * 修改详细信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/detailinfo")
	public String modifyDetailMessage(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.detailinfo";
	}
	/***
	 * 修改封面
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/moidfycover")
	public String modifyCreateCover(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.moidfycover";
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
	 * 修改权限和价格
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/powerprice")
	public String modifyPowerPrice(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=courseService.findOneById(id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.powerprice";
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
	
}
