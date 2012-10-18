package com.knet51.ccweb.controllers.teacher.blog;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.services.BlogService;
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
	@Autowired
	private BlogService blogService;

	@Transactional
	@RequestMapping(value= "/admin/blog/list", method=RequestMethod.GET)
	public String list() {
		return "admin.blog.list";
	}
	@ModelAttribute("blogPosts")
	public List<BlogPost> populateBlogPostList(HttpSession session) {
		Long id = getUserId(session);
		List<BlogPost> blogPosts = blogService.findBlogPosts(id);
		logger.debug(blogPosts.toString());
		return blogPosts;
	}
	@ModelAttribute("blogCategories")
	public List<BlogCategory> populateBlogCategoryList(HttpSession session) {
		Long id = getUserId(session);
		List<BlogCategory> blogCategories = blogService.findBlogCategories(null);
		logger.debug(blogCategories.toString());
		return blogCategories;
	}
	private Long getUserId(HttpSession session) {
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		Long id = userInfo.getId();
		return id;
	}
	@RequestMapping(value= "/admin/blog/new", method=RequestMethod.GET)
	public String show_create_form() {
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
	
	@RequestMapping(value= "/admin/blog/edit/{blog_post_id}", method=RequestMethod.GET)
	public String edit(@PathVariable Long blog_post_id, Model model) {
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
		blogPost.setDate_updated(new Date());
		
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
	public String destroy(@RequestParam("blog_post_id") Long blog_post_id) {
		//TODO: add logic to judge if there are comments attached
		blogService.deleteBlogPost(blog_post_id);
		//TODO: flash
		return "redirect:/admin/blog/list";
	}
}
