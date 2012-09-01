package com.knet51.ccweb.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User save(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User queryStringBySql(String col, String value) {
		User usr;
		TypedQuery<User> query = em.createQuery("select c from User c where c."
				+ col + " = :" + col, User.class);
		query.setParameter(col, value);
		try {
			usr = query.getSingleResult();
		} catch (NoResultException e) {
			usr = null;
		}
		return usr;
	}

	// public User findByEmailAddress(EmailAddress emailAddress) {
	//
	// TypedQuery<User> query =
	// em.createQuery("select c from User c where c.emailAddress = :email",
	// User.class);
	// query.setParameter("email", emailAddress);
	//
	// return query.getSingleResult();
	// }

}
