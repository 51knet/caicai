package com.knet51.courses.controllers.teacherCourse;


import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.Comment;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.CommentService;
import com.knet51.courses.jpa.services.TeacherCourseService;

@Controller
public class CommentInfoPageController {
	private static final Logger logger = LoggerFactory
			.getLogger(CommentInfoPageController.class);
	@Autowired
	private TeacherCourseService teacherCourseService;
	@Autowired
	private CommentService commentService;
	/**
	 * 查询出所有的教师资源
	 * @param model
	 * @param session
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@Transactional
	@RequestMapping(value= "/admin/teacherCourse/course/list", method=RequestMethod.GET)
	public String listTeacherCourses(Model model,HttpSession session, @RequestParam(value="pageNumber",defaultValue="5") int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize) {
		Page<TeacherCourse> onePage =teacherCourseService.findAllCourse(pageNumber, pageSize);
		UserInfo userinfo=(UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user=new User();
		user.setId(2L);
		session.setAttribute("user", user);
		model.addAttribute("page", onePage);
		return "admin.teacherCourse.course.list";
	}
	/**
	 * 通过ID查询出一条教师资源
	 * @param model
	 * @param session
	 * @param teacherCourse_id
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value= "/admin/teacherCourse/course/view/{id}", method=RequestMethod.GET)
	public String listCommits(Model model,HttpSession session,@PathVariable Long id, @RequestParam(value="pageNumber",defaultValue="5") int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize) {
		User user=new User();
		user.setId(2L);
		user.setName("lbx");
		Long userId=user.getId();
		session.setAttribute("user", user);
		TeacherCourse teacherCourse= teacherCourseService.findOneById(id);
		Long personNum=commentService.getPerson(id);
		Long markNum=commentService.getMark(id, userId);
		Long mark=commentService.getMark(id);
		Page<Comment> onePage=commentService.findAllCommit(pageNumber, pageSize, id);
		
		model.addAttribute("page", onePage);
		List<Comment> list=onePage.getContent();
		System.out.println(list.size());
		String title = null;
		for (Comment comment : list) {
			 title=comment.getCommentTitle();
			 System.out.println(title);
		}
		
		
		model.addAttribute("teacherCourse", teacherCourse);
		model.addAttribute("mark", mark);
		model.addAttribute("personNum", personNum);
		model.addAttribute("markNum", markNum);
		return "admin.teacherCourse.course.view";
	}
	/*@RequestMapping(value= "/admin/course/{teacherCourse_id}/list", method=RequestMethod.GET)
	public String listCommits(Model model,HttpSession session,@PathVariable Long teacherCourse_id, @RequestParam(value="pageNumber",defaultValue="5") int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize) {
		UserInfo userinfo=(UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user=userinfo.getUser();
		TeacherCourse teacherCourse= teacherCourseService.findOneById(teacherCourse_id);
		Page<Comment> page=commitService.findAllCommit(pageNumber, pageSize, user, teacherCourse);
		model.addAttribute("page", page);
		return "admin.course.list";
	}
	@RequestMapping(value= "/admin/course/{teacherCourse_id}/list", method=RequestMethod.GET)
	public String addCommits(Model model,HttpSession session,@PathVariable Long teacherCourse_id, @RequestParam(value="pageNumber",defaultValue="5") int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize) {
		UserInfo userinfo=(UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long userid=userinfo.getUser().getId();
		Comment commit=new Comment();
		//commit.set
		return "admin.course.list";
	}*/
	
	
}
