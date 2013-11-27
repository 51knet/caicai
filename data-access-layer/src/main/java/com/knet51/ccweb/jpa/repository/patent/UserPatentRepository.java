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
import com.knet51.ccweb.jpa.entities.patent.PatentType;

public interface UserPatentRepository extends JpaRepository<Patent, String>,JpaSpecificationExecutor<Patent>, UserPatentRepositoryCustom,PagingAndSortingRepository<Patent,String> {
	Page<Patent> findPatentByUser(User user, Pageable pageable);
	List<Patent> findPatentByUser(User user);
	
	Page<Patent> findPatentByPatentField(String patentField, Pageable pageable);
	List<Patent> findPatentByPatentField(String patentField);
	
	Page<Patent> findAll(Pageable pageable);
	
	Page<Patent> findPatentByPatentNumLikeAndPatentNameLikeAndInventerLike(String patentNum,String patentName,String inventer,Pageable pageable);
	
	Page<Patent> findPatentByPatentTypeAndPatentNumLike(PatentType patentType,String patentNum, Pageable pageable);
	List<Patent> findPatentByPatentTypeAndPatentNumLike(PatentType patentType,String patentNum);
	
	Page<Patent> findPatentByPatentTypeAndPatentNameLike(PatentType patentType,String patentName, Pageable pageable);
	List<Patent> findPatentByPatentTypeAndPatentNameLike(PatentType patentType,String patentName);
	
	Page<Patent> findPatentByPatentTypeAndInventerLike(PatentType patentType,String inventer, Pageable pageable);
	List<Patent> findPatentByPatentTypeAndInventerLike(PatentType patentType,String inventer);

}
