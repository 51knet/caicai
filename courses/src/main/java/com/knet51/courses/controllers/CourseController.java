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
import com.knet51.ccweb.jpa.entities.teacher.Comment;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
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
		Comment comment = commentService.getComment(id, 2l);
		Integer mark = comment.getMark().intValue();
		Integer sumMark = commentService.getMark(id).intValue();
		Integer sumPerson = commentService.getPerson(id).intValue();
		// Page<Comment> onePage = commentService.findAllCommit(pageNumber,
		// pageSize, id);
		List<Comment> listcomment = commentService.getAllCourse(id);
		model.addAttribute("id", id);
		model.addAttribute("sumMark", sumMark);
		model.addAttribute("mark", mark);
		model.addAttribute("sumPerson", sumPerson);
		model.addAttribute("listcomment", listcomment);
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
		PrintWriter out = response.getWriter();
		//Gson gson = new Gson();
		Long id = commentInfoForm.getTeachercourseid();
		Long marks = commentInfoForm.getMark();
		String commentTitle = commentInfoForm.getCommentTitle();
		// Long mark=Long.parseLong(request.getParameter("mark"));
		String commentDesc = commentInfoForm.getCommentDesc();
		logger.info("####  CourseController  ####");
		if (validResult.hasErrors()) {
			logger.info("CommentInfoForm Validation Failed " + validResult);
			 return "redirect:/teacherCourse/course/view/"+id;
		} else {
			logger.info("####  TeacherAnnoDetailController passed.  ####");
			int num = commentService.getCommentByTeacherCourseIdAndUserId(id,
					4l);
			/*
			 * UserInfo userInfo = (UserInfo)
			 * session.getAttribute(GlobalDefs.SESSION_USER_INFO); Long user_id
			 * = userInfo.getId(); User user = userService.findOne(user_id);
			 */
			if (num == 1) {
				String s="谢谢你的评论,你已评论过此课程";
				m.addAttribute("message", s);
				return "redirect:/teacherCourse/course/view/"+id;
			} else {
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
				// comment.setUserid(userid);
				commentService.createComment(comment);
				/*Comment comm = commentService.getComment(id, 4l);
				Integer mark = comm.getMark().intValue();
				Integer sumMark = commentService.getMark(id).intValue();
				Integer sumPerson = commentService.getPerson(id).intValue();*/
				// Page<Comment> onePage =
				// commentService.findAllCommit(pageNumber, pageSize, id);
				// return "redirect:/teacherCourse/course/view/"+id;
				//List<Comment> listcomment = commentService.getAllCourse(id);
			/*	m.addAttribute("sumMark", sumMark);
				m.addAttribute("mark", marks);
				m.addAttribute("sumPerson", sumPerson);
				m.addAttribute("listcomment", listcomment);*/
				// String data ="{\"mark\":"+sumMark+",\"mark\":"+marks+"}";
				/*StringBuilder data = new StringBuilder("{");
				data.append("\"sumMark\":" + sumMark + ",\"mark\":" + mark +",\"sumPerson\":"+sumPerson+",\"list:\"[");
				boolean flag=false;
				for (Comment item : listcomment) {
				data.append(",{\"commentTitle\":\""+item.getCommentTitle()+"\",\"commentDesc\":\""+item.getCommentDesc()+"\",\"commentDate\":\""+item.getCommentDate()+"\"}");
				}
				data.append("]}");
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+data);
				out.write(date);
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+gson.toJson(data));
				out.close();
				out.flush();*/
				return "redirect:/teacherCourse/course/view/"+id;
			}

		}
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
