package com.knet51.ccweb.controllers.teacher.blog;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BlogController {

	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;

	@Transactional
	@RequestMapping(value= "/admin/blog/list", method=RequestMethod.GET)
	public String list() {
		return "admin.blog.list";
	}
	@RequestMapping(value= "/admin/blog/new", method=RequestMethod.GET)
	public String create() {
		return "admin.blog.new";
	}
	@RequestMapping(value= "/admin/blog/edit/{blog_post_id}", method=RequestMethod.GET)
	public String edit() {
		return "admin.blog.edit";
	}
	@RequestMapping(value= "/admin/blog/view/{blog_post_id}", method=RequestMethod.GET)
	public String view() {
		return "admin.blog.create";
	}
	@RequestMapping(value= "/admin/blog/destroy", method=RequestMethod.GET)
	public String destroy() {
		return "/admin/blog/create";
	}
	@RequestMapping(value= "/admin/blog/category/list", method=RequestMethod.GET)
	public String list_category() {
		return "/admin/blog/create";
	}
	@RequestMapping(value= "/admin/blog/category/new", method=RequestMethod.GET)
	public String create_category() {
		return "/admin/blog/create";
	}
	@RequestMapping(value= "/admin/blog/category/edit", method=RequestMethod.GET)
	public String edit_category() {
		return "/admin/blog/create";
	}
	@RequestMapping(value= "/admin/blog/category/destroy", method=RequestMethod.GET)
	public String desctroy_category() {
		return "/admin/blog/create";
	}
	
}
