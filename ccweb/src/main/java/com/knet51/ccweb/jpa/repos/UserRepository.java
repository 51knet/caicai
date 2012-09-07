//package com.knet51.ccweb.jpa.repos;
//
//import org.springframework.data.repository.Repository;
//
//import com.knet51.ccweb.jpa.entities.EmailAddress;
//import com.knet51.ccweb.jpa.entities.User;
//
//
//
///**
// * {@link Repository} to access {@link User} instances.
// * 
// * @author
// */
//@Deprecated
//public interface UserRepository extends Repository<User, Long> {
//
//	/**
//	 * Returns the {@link User} with the given identifier.
//	 * 
//	 * @param id the id to search for.
//	 * @return
//	 */
//	User findOne(Long id);
//
//	/**
//	 * Saves the given {@link User}.
//	 * 
//	 * @param User the {@link User} to search for.
//	 * @return
//	 */
//	User save(User User);
//
//	/**
//	 * Returns the User with the given {@link EmailAddress}.
//	 * 
//	 * @param emailAddress the {@link EmailAddress} to search for.
//	 * @return
//	 */
//	User findByEmailAddress(EmailAddress emailAddress);
//}
