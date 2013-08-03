package com.knet51.ccweb.jpa.services.promotion;

import java.util.List;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;

public interface UserRecommendService {

	public List<User> getRandomUsers(String role);

	public List<User> getRandomUsers(String role, int count);

	public List<User> getRecommendTeachersFromMyTeacher(Long id);

	public List<User> getRecommendTeachersFromMyTeacher(Long id, int count);

	public List<User> getRecommendTeachersFromFriendsTeacher(Long id);

	public List<User> getRecommendTeachersFromFriendsTeacher(Long id, int count);

	public List<Course> getRandomCourses();
	
	public List<Course> getRandomCourses(int count);
	
	public List<Course> getRecommendTeachersCourses(Long id);

	public List<Course> getRecommendTeachersCourses(Long id, int count);
	
	public List<Course> getRecommendUsersCourses(Long id);

	public List<Course> getRecommendUsersCourses(Long id, int count);
	
}
