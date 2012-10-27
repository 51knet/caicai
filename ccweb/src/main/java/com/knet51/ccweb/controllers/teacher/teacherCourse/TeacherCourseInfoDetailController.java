package com.knet51.ccweb.controllers.teacher.teacherCourse;
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
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;


@Controller
public class TeacherCourseInfoDetailController {
	private static Logger logger = 
			LoggerFactory.getLogger(TeacherCourseInfoDetailController.class);
	
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private CourseResourceService courseResourceService;
	
	@Transactional
	@RequestMapping(value="/admin/teacher/teacherCourse/addCourseInfo",method=RequestMethod.POST)
	public String TeacherCourseAddInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session){
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "admin.teacher.teacherCourse.add";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			Teacher teacher = userInfo.getTeacher();
			TeacherCourse course = new TeacherCourse();
			String courseName = courseInfoForm.getCourseName();
			String courseDesc = courseInfoForm.getCourseDesc();
			course.setCourseName(courseName);
			course.setCourseDesc(courseDesc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			course.setCourseDate(date);
			course.setTeacher(teacher);
			courseService.createTeacherCourse(course);
			return "redirect:/admin/teacher/teacherCourse/detail";
		}
	
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/teacherCourse/updateCourseInfo",method=RequestMethod.POST)
	public String TeacherCourseUpdateInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session,@RequestParam("id") Long id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			System.out.println(id);
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "/admin/teacher/teacherCourse/detailOne?id="+id;
		}else{
			TeacherCourse course = new TeacherCourse();
			String courseName = courseInfoForm.getCourseName();
			String courseDesc = courseInfoForm.getCourseDesc();
			course.setCourseName(courseName);
			course.setCourseDesc(courseDesc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			course.setCourseDate(date);
			courseService.updateTeacherCourse(course);
			return "redirect:/admin/teacher/teacherCourse/detail";
		}
	
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/teacherCourse/deleCourse")
	public String TeacherCourseDele( HttpSession session,@RequestParam("id") Long id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
			courseService.deleTeacherCourse(id);
			return "redirect:/admin/teacher/teacherCourse/detail";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/teacherCourse/addInfo",method=RequestMethod.POST)
	public String TeacherCourseResourceAdd(HttpSession session,Model model,@RequestParam("id") Long course_id,
			MultipartHttpServletRequest request) throws  Exception{
		//System.out.println(course_id);
		List<MultipartFile> files = request.getFiles("file");
//		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
//		User user = userInfo.getUser();
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				CourseResource resource = new CourseResource();
				//ResourceType resourceType = new ResourceType();
				logger.info("上传文件名称"+files.get(i).getOriginalFilename()); 
				String fileName = files.get(i).getOriginalFilename();
				String realPath = session.getServletContext().getRealPath("/WEB-INF/courseResources/");
				resource.setFileName(fileName);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = format.format(new Date());
				resource.setDate(date);
				String savePath = FileUtil.saveFile(files.get(i).getInputStream(), fileName, realPath);
				resource.setSavePath(savePath);
				resource.setCourse_id(course_id);
				courseResourceService.createCourseResource(resource);
			}
		}
		return "redirect:/admin/teacher/teacherCourse/detailCourse?id="+course_id;
	}
	
}
