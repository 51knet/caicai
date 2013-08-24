package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.timeline.Trends;

public interface TrendsRepository extends JpaRepository<Trends, Long>, JpaSpecificationExecutor<Trends> {
	Page<Trends> findAllByUserId(Long user_id, Pageable pageable);
	
	List<Trends> findByUser(User user);
	
	List<Trends> findByUser(User user, Sort sort);
	
	@Query("SELECT u FROM Trends u where u.user.id IN (SELECT c.host_id FROM FriendsRelated c where c.follow_id=:uid) or u.user.id=:uid order by u.id desc")
	List<Trends> findAllByUser(@Param("uid") Long uid);
	
	@Query("SELECT u FROM Trends u where u.user.id IN (SELECT c.host_id FROM FriendsRelated c where c.follow_id=:uid)  and u.user.role =:role order by u.id desc")
	List<Trends> findAllByUserAndRole(@Param("uid") Long uid,@Param("role") String role);
	
	@Query("SELECT u FROM Trends u where u.user.id IN (SELECT c.host_id FROM FriendsRelated c where c.follow_id=:uid)  and u.variety =:variety order by u.id desc")
	List<Trends> findAllByUserAndVariety(@Param("uid") Long uid,@Param("variety") String variety);
	
	@Query("SELECT u FROM Trends u where u.user.id IN (SELECT c.host_id FROM FriendsRelated c where c.follow_id=:uid)  and u.user.role =:role and u.variety =:variety order by u.id desc")
	List<Trends> findAllByUserAndRoleAndVariety(@Param("uid") Long uid,@Param("role") String role,@Param("variety") String variety);

	@Query("SELECT u FROM Trends u where u.user.role='teacher' and u.user.id IN (SELECT c.host_id FROM FriendsRelated c where c.follow_id=:uid) or u.user.id=:uid order by u.id desc")
	List<Trends> findTeacherAllByUser(@Param("uid") Long uid);
}
