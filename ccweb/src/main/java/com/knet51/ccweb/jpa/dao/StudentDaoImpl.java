package com.knet51.ccweb.jpa.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.Student;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Student save(Student student) {
		em.persist(student);
		return student;
	}

	@Override
	public Student update(Student student) {
		em.merge(student);
		return null;
	}

	@Override
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	@Override
	public boolean delete(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Student queryStringBySql(String col, String value) {
		Student usr;
		
		TypedQuery<Student> query = em.createQuery("select c from Student c where c."
				+ col + " = :" + col, Student.class);
		query.setParameter(col, value);
		try {
			usr = query.getSingleResult();
		} catch (NoResultException e) {
			usr = null;
		}
		return usr;
	}

	@Override
	public Student getSingleResultByQuery(String query) {
		return em.createQuery(query, Student.class).getSingleResult();
	}

	@Override
	public Student getSingleResultByParamsMap(Map<String, String> paramsMap) {
		StringBuilder queryString = new StringBuilder("select c from Student c where ");
		for (String key : paramsMap.keySet()) {
			queryString.append("c.");
			queryString.append(key);
			queryString.append(" = :");
			queryString.append(key);
			queryString.append(" and ");
		}
		queryString.delete(queryString.length()-5, queryString.length());
		
		TypedQuery<Student> query = em.createQuery(queryString.toString(), Student.class);
		for (String key : paramsMap.keySet()) {
			query.setParameter(key, paramsMap.get(key));
		}
		return query.getSingleResult();
	}

	// public Student findByEmailAddress(EmailAddress emailAddress) {
	//
	// TypedQuery<Student> query =
	// em.createQuery("select c from Student c where c.emailAddress = :email",
	// Student.class);
	// query.setParameter("email", emailAddress);
	//
	// return query.getSingleResult();
	// }

}
