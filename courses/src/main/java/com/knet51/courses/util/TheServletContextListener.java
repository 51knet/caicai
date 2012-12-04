package com.knet51.courses.util;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class
 * 
 */
public class TheServletContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(TheServletContextListener.class);
	
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
		
				
//		WebApplicationContext springContext = WebApplicationContextUtils
//				.getRequiredWebApplicationContext(sce.getServletContext());
//		springContext.getAutowireCapableBeanFactory().autowireBean(this);
//		
//		EntityManagerFactory factory = (EntityManagerFactory)springContext.getBean("entityManagerFactory");
//		logger.info(factory.toString());
//		this.em = factory.createEntityManager();
//		logger.info(em.toString());
//				
//		logger.info("userService==null "+(userService==null));
//		em.getTransaction().begin();
//		clearTestDataInDB();
//		setupTestData();		
//		em.getTransaction().commit();
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
}
