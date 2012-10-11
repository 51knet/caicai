package com.knet51.ccweb.util;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Application Lifecycle Listener implementation class
 * 
 */
public class TheServletContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(TheServletContextListener.class);
	
	@Autowired
	private UserService userService;
	
	private EntityManager em;
	/**
	 * Default constructor.
	 */
	public TheServletContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		
				
		WebApplicationContext springContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(sce.getServletContext());
		springContext.getAutowireCapableBeanFactory().autowireBean(this);
		
		EntityManagerFactory factory = (EntityManagerFactory)springContext.getBean("entityManagerFactory");
		logger.info(factory.toString());
		this.em = factory.createEntityManager();
		logger.info(em.toString());
				
		logger.info("userService==null "+(userService==null));
		em.getTransaction().begin();
		clearTestDataInDB();
		setupTestData();		
		em.getTransaction().commit();
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
		create5BlogCategory();
		createTeacherAndBlogPosts("steve@apple.com","steve");
		createTeacherAndBlogPosts("jim@apple.com", "jim");
		createTeacherAndBlogPosts("bill@microsoft.com", "bill");
		createTeacherAndBlogPosts("mark@facebook.com", "mark");
		createTeacherAndBlogPosts("reggie@nintendo.com", "reggie");
	}

	private void create5BlogCategory() {
		for (int i = 1; i < 6; i++) {
			BlogCategory category = new BlogCategory("category"+(i+1));
			em.persist(category);
		}
	}
	
	private void createTeacherAndBlogPosts(String email, String password) {
		User user = new User(email,password,"user",1);
		user.setRandomUrl("pass");
		em.persist(user);
		
		Teacher teacher = new Teacher(user);
		teacher.setId(user.getId());
		teacher.setRole(0);
		em.persist(teacher);
		
		List<BlogCategory> blogCategories = em.createQuery("select c from BlogCategory c", BlogCategory.class).getResultList();
		BlogCategory blogCategory = blogCategories.get(new Random().nextInt(blogCategories.size()));
		for (int i = 1; i < 6; i++) {
			BlogPost post = new BlogPost(teacher, blogCategory, "title"+i, teacher.toString()+i);
			em.persist(post);	
		}		
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
}