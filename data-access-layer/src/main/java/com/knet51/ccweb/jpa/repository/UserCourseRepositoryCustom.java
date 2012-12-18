package com.knet51.ccweb.jpa.repository;
import java.util.List;

import com.knet51.ccweb.jpa.entities.UserCourse;

public interface UserCourseRepositoryCustom {
	List<UserCourse> findCourseByUserId(Long user_id);
}
