package com.knet51.ccweb.jpa.dao;

import java.util.List;
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
		return em.merge(user);
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

	@Override
	public List<User> list() {
		return em.createQuery("select c from User c", User.class).getResultList();
	}

//	@Override
//	public UserBeans getUserName(Long id) {
//		UserBeans userBean = new UserBeans();
//		Query q = em.createQuery("select u.name from User u where u.id=:id");
//		q.setParameter("id", id);
//		List list = q.getResultList();
//		Object o =  list.get(0);
//		String userName = o.toString();
//		userBean.setId(id);
//		userBean.setName(userName);
//		return userBean;
//	}

	@Override
	@SuppressWarnings("unchecked")
	public int getcountByEmail(String email) {
		List<User> list= em.createQuery("select u.email from User u where u.email='"+email+"'").getResultList();
		if(list.size()>0){
			return 1;
		}
		return 0;
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
