package com.knet51.ccweb.jpa.services.promotion;

import java.util.List;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;

public interface UserRecommendService {

	public List<User> getRecommendTeacher(Long user_id, int count);
	
	public List<User> getRecommendUser(Long user_id, int count);

	public List<Course> getRecommendCourses(Long user_id, int count);

}
