package com.knet51.courses.controllers.teacherCourse;


import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.knet51.ccweb.beans.UserInfo;
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
		Comment comment=commentService.getComment(id, userId);
		Long mark=comment.getMark();
		Long markNum=commentService.getMark(id);
		Page<Comment> onePage=commentService.findAllCommit(pageNumber, pageSize, id);
		model.addAttribute("page", onePage);
		/*List<Comment> list=onePage.getContent();
		System.out.println(list.size());
		String title = null;
		for (Comment comment : list) {
			 title=comment.getCommentTitle();
			 String username=comment.getUser().getName();
			 System.out.println(title);
		}*/
		model.addAttribute("teacherCourse", teacherCourse);
		model.addAttribute("mark", mark);
		model.addAttribute("personNum", personNum);
		model.addAttribute("markNum", markNum);
		return "admin.teacherCourse.course.view";
	}
	@RequestMapping(value="/admin/teacherCourse/course/view/addComment",method=RequestMethod.POST)
	public void commentAddInfo(Model model,
			 HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(value="pageNumber",defaultValue="5") int pageNumber, 
			@RequestParam(value="pageSize", defaultValue="5") int pageSize) throws Exception{
		PrintWriter out=response.getWriter();
		Gson g = new Gson();	
		String commentTitle=request.getParameter("commentTitle");
		String commentDesc=request.getParameter("commentDesc");
		long mark=Long.parseLong(request.getParameter("mark"));
		long teachercourseid=Long.parseLong(request.getParameter("teachercourseid"));
		User user =new User();
		user.setId(4l);
		Comment comment=new Comment();
		Long userid=user.getId();
		Comment com=commentService.getComment(teachercourseid, userid);
		int  value=0;
		if(com==null){
			value=1;
			out.write(value);
			out.flush();
			out.close();
		}else{
		comment.setCommentTitle(commentTitle);
		comment.setCommentDesc(commentDesc);
		comment.setMark(mark);
		comment.setTeachercourseid(teachercourseid);
		comment.setUserid(2l);
		SimpleDateFormat date=new SimpleDateFormat("yyyy-mm-dd-hh-MM-ss");
		String data=date.format(new Date());
		comment.setCommentDate(data);
		Page<Comment> onePage=commentService.findAllCommit(pageNumber, pageSize, teachercourseid);
		Integer markNum=commentService.getMark(teachercourseid).intValue();
		Integer personNum=commentService.getPerson(teachercourseid).intValue();
		String pages=onePage.toString();
		out.write(g.toJson(pages));
		out.write(markNum);
		out.wait(personNum);
		out.flush();
		out.close();
		}
	}
	
	
}
