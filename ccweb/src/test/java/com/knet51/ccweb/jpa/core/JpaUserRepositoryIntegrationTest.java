//package com.knet51.ccweb.jpa.core;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.junit.Assert.assertThat;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ActiveProfiles;
//
//import com.knet51.ccweb.jpa.entities.EmailAddress;
//import com.knet51.ccweb.jpa.entities.User;
//import com.knet51.ccweb.jpa.repos.JpaUserRepository;
//import com.knet51.ccweb.jpa.repos.UserRepository;
//
//
//
///**
// * Integration test for the manual implementation ({@link JpaUserRepository}) of the {@link UserRepository}
// * interface.
// * 
// * @author 
// */
//@ActiveProfiles("jpa")
//public class JpaUserRepositoryIntegrationTest extends AbstractIntegrationTest {
//
//	@Autowired
//	@Qualifier("jpaUserRepository")
//	UserRepository repository;
//
//	@Test
//	public void insertsNewUserCorrectly() {
//
//		User User = new User("Alicia", "Keys");
//		User = repository.save(User);
//
//		assertThat(User.getId(), is(notNullValue()));
//	}
//
//	@Test
//	public void updatesUserCorrectly() {
//
//		User dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
//		assertThat(dave, is(notNullValue()));
//
//		dave.setName("Miller");
//		dave = repository.save(dave);
//
//		User reference = repository.findByEmailAddress(dave.getEmailAddress());
//		assertThat(reference.getName(), is(dave.getName()));
//	}
//}
