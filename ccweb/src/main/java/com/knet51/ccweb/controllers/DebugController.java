package com.knet51.ccweb.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.services.BlogService;
import com.knet51.ccweb.jpa.services.UserService;
//import org.springframework.web.bind.annotation.PathVariable;
//import com.knet51.ccweb.jpa.entities.Student;
//import com.knet51.ccweb.jpa.services.StudentService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DebugController { 

	private static final Logger logger = LoggerFactory
			.getLogger(DebugController.class);
	@Autowired
	private UserService userService;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	@Qualifier("blogServiceImpl")
	private BlogService blogService;

	@Transactional
	@RequestMapping(value = "/debug", method = RequestMethod.GET)
	public String debug(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		List<User> userList = userService.findAllUsers();
		model.addAttribute("userList", userList);
		logger.info(userList.toString());
		
		BlogPost blog = blogService.findOne(1l);
		logger.info(blog.toString());
		
		User user = userService.findByEmailAddress("test1@test.com");
		UserInfo userInfo = new UserInfo(user);
		session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
		
		return "debug";
	}
	@Transactional
	@RequestMapping(value = "/debug/setup", method = RequestMethod.GET)
	public String setup(Locale locale, Model model, HttpSession session) {
		setupTestData();		
		return "debug";
	
	}
	@Transactional
	@RequestMapping(value = "/debug/teardown", method = RequestMethod.GET)
	public String teardown(Locale locale, Model model, HttpSession session) {
		clearTestDataInDB();
		return "debug";
	}

	private void clearTestDataInDB() {
		destroyAllPosts();
		destroyTeachers();
		destroyAllUsers();
		destroyBlogCategory();
	}

	private void destroyAllUsers() {
		List<User> users = em.createQuery("select c from User c", User.class).getResultList();
		 for (User user : users) {
			em.remove(user);
		}
	}

	private void destroyBlogCategory() {
		List<BlogCategory> blogCategories = em.createQuery("select c from BlogCategory c", BlogCategory.class).getResultList();
		 for (BlogCategory category : blogCategories) {
			em.remove(category);
		}
	}

	private void destroyTeachers() {
		List<Teacher> teachers = em.createQuery("select c from Teacher c", Teacher.class).getResultList();
		 for (Teacher teacher : teachers) {
			em.remove(teacher);
		}
	}

	private void destroyAllPosts() {
		 List<BlogPost> blogPosts = em.createQuery("select c from BlogPost c", BlogPost.class).getResultList();
		 for (BlogPost blogPost : blogPosts) {
			em.remove(blogPost);
		}
	}

	private void setupTestData() {
		createTeacherAndBlogPosts("steve@apple.com","steve");
		createTeacherAndBlogPosts("tim@apple.com", "tim");
		createTeacherAndBlogPosts("bill@microsoft.com", "bill");
		createTeacherAndBlogPosts("mark@facebook.com", "mark");
		createTeacherAndBlogPosts("reggie@nintendo.com", "reggie");
		createUserForTest("test@163.com","123");
	}
	
	private void createUserForTest(String email, String password){
		User user = new User(email,password,"user",1);
		user.setRandomUrl("pass");
		user.setPhoto_url("http://www.fdsm.fudan.edu.cn/UserPhotos/eb58f880-a258-48a4-aa9c-b0e7700c2abd_thumbnail.jpg");
		em.persist(user);
	}
	
	private void createTeacherAndBlogPosts(String email, String password) {
		User user = new User(email,password,"teacher",1);
		user.setName(user.getPassword());
		user.setGender("男");
		user.setRandomUrl("pass");
		user.setPhoto_url("http://www.fdsm.fudan.edu.cn/UserPhotos/eb58f880-a258-48a4-aa9c-b0e7700c2abd_thumbnail.jpg");
		em.persist(user);
		
		Teacher teacher = new Teacher(user);
		teacher.setId(user.getId());
//		teacher.setRole(0);
		em.persist(teacher);
		
		for (int i = 1; i < 6; i++) {
			BlogCategory category = new BlogCategory("category"+(i+1)+"_teacher"+teacher.getId());
			category.setAuthor(teacher);
			em.persist(category);
		}
		
		TypedQuery<BlogCategory> query = em.createQuery("select c from BlogCategory c where c.author = :author", BlogCategory.class);
		query.setParameter("author", teacher);
		List<BlogCategory> blogCategories = query.getResultList();
		BlogCategory blogCategory = blogCategories.get(new Random().nextInt(blogCategories.size()));
		for (int i = 1; i < 6; i++) {
			BlogPost post = new BlogPost(teacher, blogCategory, "title"+i, teacher.toString()+i);
			em.persist(post);	
		}		
	}
}
