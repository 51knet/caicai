package com.knet51.ccweb.jpa.repos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.EmailAddress;
import com.knet51.ccweb.jpa.entities.User;



/**
 * Plain JPA based implementation of {@link UserRepository}.
 * 
 * @author
 */
@Deprecated
@Repository
@Profile("jpa")
public class JpaUserRepository implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	/* 
	 * (non-Javadoc)
	 * @see com.oreilly.springdata.jpa.core.UserRepository#findOne(java.lang.Long)
	 */
	@Override
	public User findOne(Long id) {
		return em.find(User.class, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.oreilly.springdata.jpa.core.UserRepository#save(com.oreilly.springdata.jpa.core.User)
	 */
	public User save(User User) {
		if (User.getId() == null) {
			em.persist(User);
			return User;
		} else {
			return em.merge(User);
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see com.oreilly.springdata.jpa.core.UserRepository#findByEmailAddress(com.oreilly.springdata.jpa.core.EmailAddress)
	 */
	@Override
	public User findByEmailAddress(EmailAddress emailAddress) {

		TypedQuery<User> query = em.createQuery("select c from User c where c.emailAddress = :email",
				User.class);
		query.setParameter("email", emailAddress);

		return query.getSingleResult();
	}
}
