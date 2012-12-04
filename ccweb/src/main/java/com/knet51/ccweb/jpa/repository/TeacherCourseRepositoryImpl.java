package com.knet51.ccweb.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.beans.CourseBeans;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public class TeacherCourseRepositoryImpl implements
		TeacherCourseRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public TeacherCourse updateTeacherCourseDetail(TeacherCourse teacherCourse) {
		em.merge(teacherCourse);
		return teacherCourse;
	}

	@Override
	public List<TeacherCourse> getAllCourseById(Long id) {
		List<TeacherCourse> courseList = em.createQuery("select tc from TeacherCourse tc where teacher_id="+id).getResultList();
		return courseList;
	}

	@Override
	public List<CourseBeans> listAllCourseBeans(Long teacher_id) {
		/*List<CourseBeans> cb = em.createQuery("select new com.knet51.ccweb.beans.CourseBeans(t.college,u.name,tc) from User u,Teacher t,TeacherCourse tc where u.id=t.id and t.id=tc.teacher_id").getResultList();
		for(int i=0;i<cb.size();i++){
			System.out.println(cb.get(i).getSchoolName()+"-------"+cb.get(i).getTeacherName()+"++++++++"+cb.get(i).getCourse().getCourseName());
		}*/
		List<CourseBeans> cb = em.createQuery("select new com.knet51.ccweb.beans.CourseBeans(t,tc) from Teacher t JOIN t.course tc ").getResultList();
//		for(int i=0;i<cb.size();i++){
//			System.out.println(cb.get(i).getTeacher().getUser().getName()+"-------"+cb.get(i).getTeacher().getCollege()+"++++++++"+cb.get(i).getCourse().getCourseName());
//		}
		return cb;
	}

	@Override
	public List<String> getSchool() {
		List<String> school = em.createQuery("select distinct t.college from Teacher t JOIN t.course tc ").getResultList();
//		for(int i=0;i<school.size();i++){
//			System.out.println(school.get(i)+"==================");
//		}
		return school;
	}
	
	
	

}
