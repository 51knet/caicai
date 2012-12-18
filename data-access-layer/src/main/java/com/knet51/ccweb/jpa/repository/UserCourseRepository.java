package com.knet51.ccweb.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.knet51.ccweb.jpa.entities.UserCourse;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long>,JpaSpecificationExecutor<UserCourse>,UserCourseRepositoryCustom{

}
