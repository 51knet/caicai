package com.knet51.ccweb.jpa.repository.patent;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;

public interface UserPatentRepository extends JpaRepository<Patent, String>,JpaSpecificationExecutor<Patent>, UserPatentRepositoryCustom,PagingAndSortingRepository<Patent,String> {
	Page<Patent> findPatentByUser(User user, Pageable pageable);
	List<Patent> findPatentByUser(User user);
	
	Page<Patent> findPatentByPatentField(String patentField, Pageable pageable);
	Page<Patent> findAll(Pageable pageable);
	
	@Query("select p from Patent p where p.patentNum like :patentNum  and p.patentName like  :patentName  and p.inventer like  :inventer ")
	Page<Patent> findPatentPage(@Param("patentNum") String patentNum,@Param("patentName") String patentName, @Param("inventer") String inventer,Pageable pageable);
	
	Page<Patent> findPatentByPatentNumLikeAndPatentNameLikeAndInventerLike(String patentNum,String patentName,String inventer,Pageable pageable);
	
}
