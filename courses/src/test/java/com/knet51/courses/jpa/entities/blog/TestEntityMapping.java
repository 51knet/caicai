/**
 * 
 */
package com.knet51.courses.jpa.entities.blog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/META-INF/spring/application-context.xml"})
@Transactional
public class TestEntityMapping {
	
	@PersistenceContext
    EntityManager em;
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		BlogCategory category = new BlogCategory("category1");
//		em.persist(category);
//		User user = new User("test@test.com", "password");
//		em.persist(user);
//		Teacher teacher = new Teacher(user);
//		teacher.setId(user.getId());
//		em.persist(teacher);
//		BlogPost post = new BlogPost(teacher, category, "title", "content");
//		em.persist(post);
//		
//		Assert.assertTrue( category.getId()!=null );
//		Assert.assertTrue( post.getId()!=null );
		
	}

}
