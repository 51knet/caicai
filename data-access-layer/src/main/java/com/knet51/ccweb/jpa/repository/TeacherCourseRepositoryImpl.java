package com.knet51.ccweb.jpa.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.knet51.ccweb.jpa.entities.Teacher;
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
	public List<TeacherCourse> getAllCourseById(Long teacher_id) {
		List<TeacherCourse> courseList = em.createQuery("select tc from TeacherCourse tc where teacher_id="+teacher_id).getResultList();
		return courseList;
	}


//	@Override
//	public List<CourseBeans> listAllCourseBeans() {
//		/*List<CourseBeans> cb = em.createQuery("select new com.knet51.ccweb.beans.CourseBeans(t.college,u.name,tc) from User u,Teacher t,TeacherCourse tc where u.id=t.id and t.id=tc.teacher_id").getResultList();
//		for(int i=0;i<cb.size();i++){
//			System.out.println(cb.get(i).getSchoolName()+"-------"+cb.get(i).getTeacherName()+"++++++++"+cb.get(i).getCourse().getCourseName());
//		}*/
//		List<CourseBeans> cb = em.createQuery("select new com.knet51.ccweb.beans.CourseBeans(t,tc) from Teacher t JOIN t.course tc ").getResultList();
//		return cb;
//	}




	@Override
	public List<String> getSchool() {
		List<String> school = em.createQuery("select distinct t.college from Teacher t JOIN t.course tc ").getResultList();
		return school;
	}

	@Override
	public List<Teacher> showCourseTeacher(String schoolName) {
		if(schoolName!=null &&schoolName!=""){
			List<Teacher> teacher = em.createQuery("select distinct t from Teacher t JOIN t.course tc where t.college='"+schoolName+"'").getResultList();
			return teacher;
		}else{
			List<Teacher> teacher = em.createQuery("select distinct t from Teacher t JOIN t.course tc").getResultList();
			return teacher;
		}
	
	}

	@Override
	public List<String> getCourseType() {
		List<String> typeList = em.createQuery("select distinct c.courseType from TeacherCourse c").getResultList();
		return typeList;
	}
	
	
	
	
	

}
