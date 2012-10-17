package com.knet51.ccweb.controllers.teacher.blog;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.services.BlogService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BlogCategoryController {

	private static final Logger logger = LoggerFactory.getLogger(BlogCategoryController.class);

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	@Qualifier("blogServiceImpl")
	private BlogService blogService;
	
	
	// @ModelAttribute("blogCategories")
	public List<BlogCategory> populateBlogCategoryList(HttpSession session) {
		Long id = getUserId(session);
		List<BlogCategory> blogCategories = blogService.findBlogCategories(id);
		logger.debug(blogCategories.toString());
		return blogCategories;
	}
	private Long getUserId(HttpSession session) {
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		Long id = userInfo.getId();
		return id;
	}
	
	@RequestMapping(value= "/admin/blog/category/list", method=RequestMethod.GET)
	public String list_category(Model model, HttpSession session) {
		List<BlogCategory> list = populateBlogCategoryList(session);
		model.addAttribute("blogCategories", list);
		return "admin.blog.category.list";
	}
	/*
	 * @RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> create(@RequestBody Account account, HttpServletResponse response) {
		Set<ConstraintViolation<Account>> failures = validator.validate(account);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return validationMessages(failures);
		} else {
			accounts.put(account.assignId(), account);
			return Collections.singletonMap("id", account.getId());
		}
	}
	 */
	// AJAX
	@RequestMapping(value= "/admin/blog/category/new", method=RequestMethod.POST)
	public ModelAndView show_create_category_form(@RequestBody BlogCategory blogCategory) {
		//TDDO: check category exist or not		
		ModelAndView mv = new ModelAndView("admin.blog.category.new");
		blogCategory = blogService.createBlogCategory(blogCategory);
		mv.addObject("category", blogCategory);
		return mv;
	}
	
	@RequestMapping(value= "/admin/blog/category/rename", method=RequestMethod.POST)
	public ModelAndView rename_category(@RequestBody BlogCategoryRenameFrom form) {
		//TDDO: check category exist or not		
		ModelAndView mv = new ModelAndView("admin.blog.category.rename");
		BlogCategory blogCategory = blogService.findBlogCategory(form.getId());
		blogCategory.setName(form.getName());
		blogCategory = blogService.renameBlogCategory(blogCategory);
		mv.addObject("category", blogCategory);
		return mv;
	}
	
	@RequestMapping(value= "/admin/blog/category/new", method=RequestMethod.GET)
	public String create_category() {
		return "/admin/blog/create";
	}
	@RequestMapping(value= "/admin/blog/category/edit", method=RequestMethod.GET)
	public String edit_category() {
		return "/admin/blog/create";
	}
	@RequestMapping(value= "/admin/blog/category/destroy", method=RequestMethod.POST)
	public String desctroy_category(@RequestParam Long blog_category_id) {
		//TODO: add logic to judge if there are posts attached
		blogService.deleteBlogCategory(blog_category_id);
		//TODO: flash
		return "redirect:/admin/blog/category/list";
	}
	
}
