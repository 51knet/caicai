package com.knet51.courses.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.entities.teacher.UserCourse;
import com.knet51.courses.beans.UserCourseBeans;
import com.knet51.courses.jpa.services.ResourceService;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserCourseService;
import com.knet51.courses.util.ajax.AjaxValidationEngine;
import com.knet51.courses.util.ajax.ValidationResponse;
import com.knet51.courses.util.fileUpLoad.FileUtil;
	
@Controller
public class CourseController {
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private ResourceService courseResourceService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private TeacherService teacherService;

	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);

	// @RequestMapping(value="/course/list")
	// public String showAllCourse(Model model){
	// // List<TeacherCourseBeans> tcBeanList =
	// courseService.getAllTeacherCourseBeans();
	// // model.addAttribute("courseList", tcBeanList);
	// List<TeacherCourse> courseList = courseService.findAllCourses();
	// List<String> courseTypeList = courseService.courseTypeList();
	// model.addAttribute("courseList", courseList);
	// model.addAttribute("courseCount", courseList.size());
	// model.addAttribute("courseTypeList", courseTypeList);
	// return "course.list";
	// }
	@Transactional
	@RequestMapping(value = "/course/list/type")
	public String showCourseByType(@RequestParam("detail") String courseType,
			Model model) throws Exception {
		courseType = new String(courseType.getBytes("iso-8859-1"), "utf-8");
		List<TeacherCourse> courseList = courseService.findAllCourses();
		List<String> courseTypeList = courseService.courseTypeList();
		List<TeacherCourse> newCourseList = new ArrayList<TeacherCourse>();
		if (courseType.trim() != null && !courseType.trim().equals("全部课程")
				&& !courseType.trim().equals("all")) {
			for (TeacherCourse c : courseList) {
				if (courseType.equals(c.getCourseType())) {
					newCourseList.add(c);
				}
			}
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseList", newCourseList);
			model.addAttribute("courseCount", newCourseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
			
		} else {
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseCount", courseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
		}
		return "course.list";
	}
	@RequestMapping(value="/course/view/{course_id}")
	public String showCourseDetail(@PathVariable Long course_id,Model model,HttpSession session
			,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
		//UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		/*     zm    */
		TeacherCourse course = courseService.findOneById(course_id);
		model.addAttribute("course", course);
		/*     lbx    */
		UserCourse  userCourse;
		userCourse=userCourseService.findByTeachercourseidAndUserid(course_id, 4L);
		if(userCourse==null){
			userCourse=new UserCourse();
			userCourse.setUserid(4L);
			userCourse.setTeachercourseid(course_id);
			userCourseService.save(userCourse);
		}
		Teacher teacher = course.getTeacher();
		List<UserCourse> listUserCourse = userCourseService.findByTeachercourseid(course_id);
		Integer sumPerson=listUserCourse.size();
		double courseMark=0.0;
		if(userCourse.getMark()!=null){
			 courseMark=userCourseService.getMark(course_id);//一个视频的评论平均分数
		}
		List<UserCourseBeans> list=new ArrayList<UserCourseBeans>();
		Page<UserCourse> onePage = userCourseService.findUserCourseByTeachercourseid(pageNumber,pageSize, course_id);
		UserCourseBeans UserCourseUser;
		for (int i = 0; i < onePage.getContent().size(); i++) {
			UserCourseUser=new UserCourseBeans();
			long userid=onePage.getContent().get(i).getUserid();
			User user=userCourseService.findByUserId(userid);
			UserCourse comm=onePage.getContent().get(i);
			if(comm.getCommentDesc()!=null){
				UserCourseUser.setUserCourse(comm);
			}
			String userName=user.getName();
			String photoUrl=user.getPhoto_url();
			UserCourseUser.setPhotoUrl(photoUrl);
			UserCourseUser.setUserName(userName);
			list.add(UserCourseUser);
		}
		model.addAttribute("teacher", teacher);
		
		List<CourseResource> listResource = courseResourceService.getResourceByCourseId(course_id);
		List<CourseResource> courseList;
		Map<String, List<CourseResource>> courseMap = new TreeMap<String, List<CourseResource>>();
		String resourceOrder = null;
		for (CourseResource courseResource : listResource) {
			resourceOrder = courseResource.getCourseOrder();
			courseList= new ArrayList<CourseResource>();
			courseList = courseResourceService.getResourceByCourseOrderAndCourseId(resourceOrder,course_id);
			courseMap.put(resourceOrder, courseList);
		}
		model.addAttribute("page", onePage);
		model.addAttribute("listUserCourse", list);
		//model.addAttribute("id", id);
		model.addAttribute("sumPerson", sumPerson);
		model.addAttribute("courseMark", courseMark);
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("resourceCount", listResource.size());
		return "course.list.view";
	}

	/**
	 * 通过ID查询出一条课程详细资料
	 * 
	 * @param model
	 * @param session
	 * @param teacherCourse_id
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/teachercourse/course/view/{id}")
	public String listCourseByTeacherCourseId(
			Model model,
			HttpSession session,
			@PathVariable Long id) {
		List<CourseResource> listResource = courseResourceService
				.getResourceByCourseId(id);
		List<CourseResource> courseList = new ArrayList<CourseResource>();
		Map<String, List<CourseResource>> courseMap = new TreeMap<String, List<CourseResource>>();
		String resourceOrder = null;
		for (CourseResource courseResource : listResource) {
			resourceOrder = courseResource.getCourseOrder();
			courseList = courseResourceService
					.getResourceByCourseOrderAndCourseId(resourceOrder,id);
			courseMap.put(resourceOrder, courseList);
		}
		model.addAttribute("courseMap", courseMap);
		/*    zm   */
		TeacherCourse teacherCourse = courseService.findOneById(id);
		model.addAttribute("course", teacherCourse);
		model.addAttribute("resourceCount", listResource.size());
		return "teachercourse.course.view";
	}
	/**
	 * 通过ID查询出一条课程资料
	 * 
	 * @param model
	 * @param session
	 * @param teacherCourse_id
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/teachercourse/course/courseInfo/{id}")
	public String coursesByTeacherCourseId(
			Model model,
			HttpSession session,
			@PathVariable Long id) {
		TeacherCourse teacherCourse=courseService.findOneById(id);
		model.addAttribute("course",teacherCourse );
		return "teachercourse.course.courseInfo";
	}
	/**
	 * 查询出相关的评论信息
	 * @param validResult
	 * @param request
	 * @param session
	 * @param m
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teachercourse/course/usercourse/{id}")
	public String listUserCourse(@PathVariable Long id, Model model,HttpSession session
			,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize)
			throws Exception {	
		List<UserCourseBeans> list=new ArrayList<UserCourseBeans>();
		Page<UserCourse> onePage = userCourseService.findUserCourseByTeachercourseid(pageNumber,pageSize, id);
		UserCourseBeans UserCourseUser;
		for (int i = 0; i < onePage.getContent().size(); i++) {
			UserCourseUser=new UserCourseBeans();
			long userid=onePage.getContent().get(i).getUserid();
			User user=userCourseService.findByUserId(userid);
			UserCourse comm=onePage.getContent().get(i);
			String userName=user.getName();
			String photoUrl=user.getPhoto_url();
			UserCourseUser.setUserCourse(comm);
			UserCourseUser.setPhotoUrl(photoUrl);
			UserCourseUser.setUserName(userName);
			list.add(UserCourseUser);
		}
		Integer sumPerson=list.size();
		List<UserCourse> userCourseList=userCourseService.findByTeachercourseid(id);
		double courseMark=0.0;
		for (UserCourse userCourse : userCourseList) {
			if(userCourse.getMark()!=null){
				 courseMark=userCourseService.getMark(id);//一个视频的评论平均分数
			}
		}
		TeacherCourse teacherCourse = courseService.findOneById(id);
		model.addAttribute("course", teacherCourse);
		//model.addAttribute("listCount", listUserCourse.size());
		model.addAttribute("listUserCourse", list);
		//model.addAttribute("id", id);
		model.addAttribute("page", onePage);
		model.addAttribute("sumPerson", sumPerson);
		model.addAttribute("courseMark", courseMark);
		return "teachercourse.course.usercourse";

	}
	
	/**
	 * 增加评论内容
	 * @param UserCourseInfoForm
	 * @param id
	 * @param model
	 * @param session
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teachercourse/course/usercourse/new", method = RequestMethod.POST)
	public String contactInfo( @RequestParam("teachercourseid") Long id,@Valid UserCourseForm userCourseForm,BindingResult validResult,RedirectAttributes redirectAttr, HttpSession session) {
		logger.info("#### contactInfo InfoController ####");
		Long marks = userCourseForm.getMark();
		String userCourseDesc = userCourseForm.getCommentDesc().trim();
		//String message="";
		if (validResult.hasErrors()) {
			logger.info("contactInfo Validation Failed " + validResult);
			return "redirect:/teachercourse/course/usercourse/"+id;
		} else {
			logger.info("### contactInfo Validation passed. ###");
			//UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			UserCourse userCourse=userCourseService.findByTeachercourseidAndUserid(id, 4l);
			if (userCourse.getCommentDesc()!=null&&userCourse.getMark()>=0) {
				String message="请不要重复评论";
				redirectAttr.addFlashAttribute("message", message);
				return "redirect:/teachercourse/course/usercourse/"+id;
			} else{
				UserCourse comm = userCourseService.findByTeachercourseidAndUserid(id, 4l);
				if(userCourseDesc!=null&&!(userCourseDesc.equals(""))){
					comm.setCommentDesc(userCourseDesc);
					comm.setMark(marks);
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = format.format(new Date());
					comm.setCommentDate(date);
					userCourseService.update(comm);
				}
				return "redirect:/teachercourse/course/usercourse/"+id;
			}
		}
	}
	/**
	 * 下载视频
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception    
	 */
	@RequestMapping(value = "/teachercourse/course/view/resource/{id}")
	public String resourceDownLoad(@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CourseResource courseResource=courseResourceService.findById(id);
		String savePath = courseResource.getSavePath();
		String fileName = courseResource.getSaveName();
		FileUtil.downLoad(request, response, savePath, fileName);
		return null;
	}

	/**
	 * 验证输入框是否为空
	 * 
	 * @param UserCourseInfoForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/teachercourse/course/usercourse/usercourseajax", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse UserCourseInfoFormAjaxJson(
			@Valid UserCourseForm userCourseForm,BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
}
