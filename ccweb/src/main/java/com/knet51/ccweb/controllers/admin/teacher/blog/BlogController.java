package com.knet51.ccweb.controllers.admin.teacher.blog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import com.knet51.ccweb.util.message.FlashMessage;
import com.knet51.ccweb.util.message.MessageType;

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
	@Autowired
	private BlogService blogService;

	@Transactional
	@RequestMapping(value= "/admin/blog/list", method=RequestMethod.GET)
	public String list(@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize,
			@RequestParam(value="type",defaultValue="all") String type,
			Model model, HttpSession session) {
		Long id = getUserId(session);
		Teacher teacher = teacherService.findOne(id);
		
		Page<BlogPost> page = blogService.findAllBlogsNotGarbage(pageNumber, pageSize, teacher);
		if (type.equals("garbage")) {
			page = blogService.findAllBlogsIsGarbage(pageNumber, pageSize, teacher);
		} else if (type.equals("draft")) {
			page = blogService.findAllBlogsIsDraftNotGarbage(pageNumber, pageSize, teacher);
		}
		model.addAttribute("page", page);
		return "admin.blog.list";
	}
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
	@RequestMapping(value= "/admin/blog/new", method=RequestMethod.GET)
	public String show_create_form(Model model, HttpSession session) {
		List<BlogCategory> blogCategories = populateBlogCategoryList(session);
		model.addAttribute("blogCategories", blogCategories);
		return "admin.blog.new";
	}
	@RequestMapping(value= "/admin/blog/new", method=RequestMethod.POST)
	public String create(@Valid BlogPost blogPost, BindingResult result, @RequestParam("blogcategory_id") Long blogcategory_id, HttpSession session) {
		if (result.hasErrors()) {
			logger.info("Validation Failed " + result);
			return "admin.blog.new";
		}
		logger.info(blogcategory_id.toString());
		logger.info(blogPost.toString());
		BlogCategory blogCategory = blogService.findBlogCategory(blogcategory_id);
		Teacher teacher = teacherService.findOne(getUserId(session));
		blogPost.setBlogCategory(blogCategory);
		blogPost.setAuthor(teacher);
		blogService.createBlogPost(blogPost);
		
		return "redirect:/admin/blog/list";
	}
	
	@RequestMapping(value= "/admin/blog/comment/new", method=RequestMethod.POST)
	public String create(@Valid BlogComment blogComment, BindingResult result, @RequestParam("blogpost_id") Long blogpost_id, Model model, HttpSession session) {
		BlogPost blogPost = blogService.findOne(blogpost_id);
		if (result.hasErrors()) {
			logger.info("Validation Failed " + result);
			//model.addAttribute("blogPost", blogPost);
			return "admin.blog.view";
		}
		
		Teacher teacher = teacherService.findOne(getUserId(session));
		blogComment.setAuthor(teacher);
		blogComment.setBlogPost(blogPost);
		blogService.createBlogComment(blogComment);
		
		return "redirect:/admin/blog/view/"+blogpost_id;
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
	
	@RequestMapping(value= "/admin/blog/edit/{blog_post_id}", method=RequestMethod.GET)
	public String edit(@PathVariable Long blog_post_id, Model model, HttpSession session) {
		List<BlogCategory> blogCategories = populateBlogCategoryList(session);
		model.addAttribute("blogCategories", blogCategories);
		
		BlogPost blogPost = blogService.findOne(blog_post_id);
		logger.info(blogPost.toString());
		model.addAttribute("blogPost", blogPost);
		
		return "admin.blog.edit";
	}
	@RequestMapping(value= "/admin/blog/edit/{blog_post_id}", method=RequestMethod.POST)
	public String edit(@Valid BlogPost blogPostForm, BindingResult result, @RequestParam("blogcategory_id") Long blogcategory_id, @PathVariable Long blog_post_id, HttpSession session) {
		BlogPost blogPost = blogService.findOne(blog_post_id);
		if(blogcategory_id != blogPost.getBlogCategory().getId()) {
			BlogCategory blogCategory = blogService.findBlogCategory(Long.valueOf(blogcategory_id));
			blogPost.setBlogCategory(blogCategory);
		}
		blogPost.setTitle(blogPostForm.getTitle());
		blogPost.setContent(blogPostForm.getContent());
		blogPost.setDateUpdated(new Date());
		blogPost.setDraft(blogPostForm.isDraft());
		
		blogService.updateBlogPost(blogPost);
		
		return "redirect:/admin/blog/list";
	}
	@RequestMapping(value= "/admin/blog/view/{blog_post_id}", method=RequestMethod.GET)
	public String view(@PathVariable Long blog_post_id, Model model) {
		BlogPost blogPost = blogService.findOne(blog_post_id);
		model.addAttribute("blogPost", blogPost);
		return "admin.blog.view";
	}
	@RequestMapping(value= "/admin/blog/destroy", method=RequestMethod.POST)
	public String destroy(@RequestParam("blog_post_id") Long blog_post_id, RedirectAttributes redirectAttrs) {
		//DONE: no need to add logic to judge if there are comments attached, we just mark it in garbage can.
		//blogService.deleteBlogPost(blog_post_id);
		BlogPost blogPost = blogService.findOne(blog_post_id);
		blogPost.setGarbage(true);
		blogService.updateBlogPost(blogPost);
		
		FlashMessage flash = new FlashMessage(MessageType.info, "成功移到回收站");
		redirectAttrs.addFlashAttribute("flash", flash);
		return "redirect:/admin/blog/list";
	}
}
