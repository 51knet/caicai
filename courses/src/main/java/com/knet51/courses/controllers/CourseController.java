package com.knet51.courses.controllers;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.Comment;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.beans.CommentUserBeans;
import com.knet51.courses.jpa.services.CommentService;
import com.knet51.courses.jpa.services.CourseService;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.util.ajax.AjaxValidationEngine;
import com.knet51.courses.util.ajax.ValidationResponse;

@Controller
public class CourseController {
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private CourseService courseResourceService;
	@Autowired
	private CommentService commentService;
	
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
			return "course.list";
		} else {
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseCount", courseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
			return "course.list";
		}
	}
	
	@RequestMapping(value="/course/view/{course_id}")
	public String showCourseDetail(@PathVariable Long course_id,Model model){
		TeacherCourse course = courseService.findOneById(course_id);
		List<CourseResource> listCourse = courseResourceService
				.getResourceByCourseId(course_id);
		
		Double courseMark = commentService.getMark(course_id);
		model.addAttribute("courseMark", courseMark);
		model.addAttribute("userCount", commentService.getPerson(course_id));
		
		List<CourseResource> listCourses = new ArrayList<CourseResource>();
		Map<String, List<CourseResource>> courseMap = new LinkedHashMap<String, List<CourseResource>>();
		String resourceOrder = null;
		for (CourseResource courseResource : listCourse) {
			resourceOrder = courseResource.getResourceOrder();
			listCourses = courseResourceService
					.getResourceByResourceOrder(resourceOrder);
			courseMap.put(resourceOrder, listCourses);
		}
		Comment comment=new Comment();
		Integer sumMark=0;
		Integer mark=0;
		
		List<CommentUserBeans> list=new ArrayList<CommentUserBeans>();
		List<Comment> listcomment =new ArrayList<Comment>();
		try {
			 listcomment = commentService.getAllCourse(course_id);
			if(listcomment.size()>0){
				comment = commentService.getComment(course_id, 4l);
				mark = comment.getMark().intValue();
				sumMark = commentService.getMark(course_id).intValue();
				
			for (int i = 0; i < listcomment.size(); i++) {
				User user=commentService.getByUser(listcomment.get(i).getUserid());
				String commentTitle=listcomment.get(i).getCommentTitle();
				String commentDate=listcomment.get(i).getCommentDate();
				String commentDesc=listcomment.get(i).getCommentDesc();
				String name=user.getName();
				CommentUserBeans commentUser=new CommentUserBeans();
				 commentUser.setName(name);
				 commentUser.setCommentTitle(commentTitle);
				 commentUser.setCommentDesc(commentDesc);
				 commentUser.setCommentDate(commentDate);
				list.add(commentUser);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("id", course_id);
		model.addAttribute("sumMark",sumMark);
		model.addAttribute("mark", mark);
		model.addAttribute("sumPerson", commentService.getPerson(course_id));
		model.addAttribute("listcomment", list);
		model.addAttribute("courseMap", courseMap);
		Teacher teacher = course.getTeacher();
		model.addAttribute("course", course);
		model.addAttribute("teacher", teacher);
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
	@RequestMapping(value = "/teacherCourse/course/view/{id}", method = RequestMethod.GET)
	public String listCourseByTeacherCourseId(
			Model model,
			HttpSession session,
			@PathVariable Long id,
			@RequestParam(value = "pageNumber", defaultValue = "5") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		List<CourseResource> listCourse = courseResourceService
				.getResourceByCourseId(id);
		List<CourseResource> listCourses = new ArrayList<CourseResource>();
		Map<String, List<CourseResource>> courseMap = new LinkedHashMap<String, List<CourseResource>>();
		String resourceOrder = null;
		for (CourseResource courseResource : listCourse) {
			resourceOrder = courseResource.getResourceOrder();
			listCourses = courseResourceService
					.getResourceByResourceOrder(resourceOrder);
			courseMap.put(resourceOrder, listCourses);
		}
		// model.addAttribute("listCourse", listCourse);
		model.addAttribute("courseMap", courseMap);
		// Page<Comment> onePage = commentService.findAllCommit(pageNumber,
		// pageSize, id);
		Comment comment=new Comment();
		Integer sumMark=0;
		Integer mark=0;
		Integer sumPerson=0;
		List<CommentUserBeans> list=new ArrayList<CommentUserBeans>();
		List<Comment> listcomment =new ArrayList<Comment>();
		String message="";
		try {
			 listcomment = commentService.getAllCourse(id);
			if(listcomment.size()>0){
				comment = commentService.getComment(id, 4l);
				int num = commentService.getCommentByTeacherCourseIdAndUserId(id,4l);
				if (num == 1) {
					message="请不要重复评论";
				}
				mark = comment.getMark().intValue();
				sumMark = commentService.getMark(id).intValue();
				sumPerson = commentService.getPerson(id).intValue();
			for (int i = 0; i < listcomment.size(); i++) {
				User user=commentService.getByUser(listcomment.get(i).getUserid());
				String commentTitle=listcomment.get(i).getCommentTitle();
				String commentDate=listcomment.get(i).getCommentDate();
				String commentDesc=listcomment.get(i).getCommentDesc();
				String name=user.getName();
				CommentUserBeans commentUser=new CommentUserBeans();
				 commentUser.setName(name);
				 commentUser.setCommentTitle(commentTitle);
				 commentUser.setCommentDesc(commentDesc);
				 commentUser.setCommentDate(commentDate);
				list.add(commentUser);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		model.addAttribute("id", id);
		model.addAttribute("sumMark", sumMark);
		model.addAttribute("mark", mark);
		model.addAttribute("sumPerson", sumPerson);
		model.addAttribute("listcomment", list);
		return "teacherCourse.course.view";
	}

	/**
	 * 增加评论内容
	 * 
	 * @param validResult
	 * @param request
	 * @param session
	 * @param m
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teacherCourse/course/view/comment", method = RequestMethod.POST)
	public String commentAddInfo(
			@Valid CommentInfoForm commentInfoForm,
			BindingResult validResult,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Model m,
			@RequestParam(value = "pageNumber", defaultValue = "5") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize)
			throws Exception {
		Long id = commentInfoForm.getTeachercourseid();
		Long marks = commentInfoForm.getMark();
		String commentTitle = commentInfoForm.getCommentTitle();
		String commentDesc = commentInfoForm.getCommentDesc();
		logger.info("####  CourseController  ####");
		if (validResult.hasErrors()) {
			logger.info("CommentInfoForm Validation Failed " + validResult);
			 return "redirect:/teacherCourse/course/view/"+id;
		} else {
			logger.info("####  TeacherAnnoDetailController passed.  ####");
			try {
				int num = commentService.getCommentByTeacherCourseIdAndUserId(id,4l);
				if (num == 1) {
					return "redirect:/teacherCourse/course/view/"+id;
				} else if(num==0) {
					Comment comment = new Comment();
					comment.setCommentTitle(commentTitle);
					comment.setCommentDesc(commentDesc);
					comment.setMark(marks);
					comment.setTeachercourseid(id);
					comment.setUserid(4l);
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String date = format.format(new Date());
					comment.setCommentDate(date);
					commentService.createComment(comment);
					return "redirect:/teacherCourse/course/view/"+id;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			

		}
		return "redirect:/teacherCourse/course/view/"+id;
	}

	/**
	 * 验证输入框是否为空
	 * 
	 * @param commentInfoForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/teacherCourse/course/view/commentAjax", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse commentInfoFormAjaxJson(
			@Valid CommentInfoForm commentInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}

}
