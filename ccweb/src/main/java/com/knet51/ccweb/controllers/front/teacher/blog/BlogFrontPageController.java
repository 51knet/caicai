package com.knet51.ccweb.controllers.front.teacher.blog;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.entities.blog.BlogComment;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.services.BlogService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BlogFrontPageController {

	private static final Logger logger = LoggerFactory.getLogger(BlogFrontPageController.class);

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;

	@RequestMapping(value= "/teacher/{teacher_id}/blog/list", method=RequestMethod.GET)
	public String list(@PathVariable Long teacher_id, @RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize, Model model) {
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		
		//Page<BlogPost> page = blogService.findAllBlogs(pageNumber, pageSize, teacher);
		Page<BlogPost> page = blogService.findAllBlogsNotGarbageAndNotDraft(pageNumber, pageSize, teacher);
		model.addAttribute("page", page);
		return "teacher.blog.list";
	}
	@RequestMapping(value= "/teacher/{teacher_id}/blog/view/{blog_post_id}", method=RequestMethod.GET)
	public String view(@PathVariable Long teacher_id, @PathVariable Long blog_post_id, Model model) {
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		
		BlogPost blogPost = blogService.findOne(blog_post_id);
		List<BlogComment> blogCommentList=(List<BlogComment>) blogPost.getBlogComments();
		model.addAttribute("blogPost", blogPost);
		model.addAttribute("blogCommentList", blogCommentList);
		model.addAttribute("sumComment", blogPost.getBlogComments().size());
		
		return "teacher.blog.view";
	}
	//@ModelAttribute("blogPosts")
	public List<BlogPost> populateBlogPostList(HttpSession session) {
		Long id = getUserId(session);
		List<BlogPost> blogPosts = blogService.findBlogPosts(id);
		logger.debug(blogPosts.toString());
		return blogPosts;
	}
	//@ModelAttribute("blogCategories")
	public List<BlogCategory> populateBlogCategoryList(HttpSession session) {
		Long id = getUserId(session);
		Teacher teacher = teacherService.findOne(id);
		List<BlogCategory> blogCategories = blogService.findBlogCategories(teacher);
		logger.debug(blogCategories.toString());
		return blogCategories;
	}
	private Long getUserId(HttpSession session) {
		UserInfo userInfo = (UserInfo)session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long id = userInfo.getId();
		return id;
	}
	@RequestMapping(value= "/teacher/{teacher_id}/blog/comment", method=RequestMethod.POST)
	public String view(@PathVariable Long teacher_id, @Valid BlogComment blogComment, BindingResult result, @RequestParam("blogpost_id") Long blogpost_id, Model model, HttpSession session) {
		BlogPost blogPost = blogService.findOne(blogpost_id);
		if (result.hasErrors()) {
			logger.info("Validation Failed " + result);
			model.addAttribute("blogPost", blogPost);
			return "teacher.blog.view";
		}
		
		Teacher teacher = teacherService.findOne(getUserId(session));
		blogComment.setAuthor(teacher);
		blogComment.setBlogPost(blogPost);
		blogService.createBlogComment(blogComment);
		
		return "redirect:/teacher/"+teacher_id+"/blog/view/"+blogpost_id;
		
	}
}
