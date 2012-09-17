package com.knet51.ccweb.jpa.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.Teacher;

@Repository("teacherDao")
public class TeacherDaoImpl implements TeacherDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Teacher save(Teacher teacher) {
		em.persist(teacher);
		return teacher;
	}

	@Override
	public Teacher update(Teacher teacher) {
		teacher = em.merge(teacher);
		return teacher;
	}

	@Override
	public Teacher findById(Long id) {
		return em.find(Teacher.class, id);
	}

	@Override
	public boolean delete(Teacher teacher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Teacher queryStringBySql(String col, String value) {
		Teacher usr;

		TypedQuery<Teacher> query = em.createQuery(
				"select c from teacher c join c.user where c." + col + " = :" + col,
				Teacher.class);
		query.setParameter(col, value);
		try {
			usr = query.getSingleResult();
		} catch (NoResultException e) {
			usr = null;
		}
		return usr;
	}

	@Override
	public Teacher getSingleResultByQuery(String query) {
		return em.createQuery(query, Teacher.class).getSingleResult();
	}

	@Override
	public Teacher getSingleResultByParamsMap(Map<String, String> paramsMap) {
		StringBuilder queryString = new StringBuilder(
				"select c from Teacher c where ");
		for (String key : paramsMap.keySet()) {
			queryString.append("c.");
			queryString.append(key);
			queryString.append(" = :");
			queryString.append(key);
			queryString.append(" and ");
		}
		queryString.delete(queryString.length() - 5, queryString.length());

		TypedQuery<Teacher> query = em.createQuery(queryString.toString(),
				Teacher.class);
		for (String key : paramsMap.keySet()) {
			query.setParameter(key, paramsMap.get(key));
		}
		return query.getSingleResult();
	}

//	@Override
//	public int queryCountBySql(String col, String value) {
//
//		Query query = em.createNativeQuery("select "+col+" from Teacher where "
//				+ col + " = :" + col);
//		query.setParameter(col, value);
//
//		List objList = query.getResultList();
//		if (objList != null) {
//			return objList.size();
//		} else {
//			return 0;
//		}
//	}

}
