package com.knet51.ccweb.jpa.dao;

import java.util.Map;

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

	@Override
	public User getSingleResultByQuery(String query) {
		return em.createQuery(query, User.class).getSingleResult();
	}

	@Override
	public User getSingleResultByParamsMap(Map<String, String> paramsMap) {
		StringBuilder queryString = new StringBuilder("select c from User c where ");
		for (String key : paramsMap.keySet()) {
			queryString.append("c.");
			queryString.append(key);
			queryString.append(" = :");
			queryString.append(key);
			queryString.append(" and ");
		}
		queryString.delete(queryString.length()-5, queryString.length());
		
		TypedQuery<User> query = em.createQuery(queryString.toString(), User.class);
		for (String key : paramsMap.keySet()) {
			query.setParameter(key, paramsMap.get(key));
		}
		return query.getSingleResult();
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
