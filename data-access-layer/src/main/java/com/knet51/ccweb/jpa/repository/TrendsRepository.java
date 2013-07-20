package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.knet51.ccweb.jpa.entities.timeline.Trends;

public interface TrendsRepository extends JpaRepository<Trends, Long>, JpaSpecificationExecutor<Trends> {
	Page<Trends> findAllByUserId(Long user_id, Pageable pageable);
	
	List<Trends> findByUserId(Long user_id );
	
	List<Trends> findByUserId(Long user_id, Sort sort);
	
	@Query("SELECT t FROM Trends t where t.userId IN (SELECT r.host_id FROM FriendsRelated r where r.follow_id=:uid) or t.userId=:uid order by id desc")
	List<Trends> findAllByUserId(@Param("uid") Long uid);

	@Query("SELECT t FROM Trends t where t.role='teacher' and t.userId IN (SELECT r.host_id FROM FriendsRelated r where r.follow_id=:uid) or t.userId=:uid order by id desc")
	List<Trends> findTeacherAllByUserId(@Param("uid") Long uid);
}
