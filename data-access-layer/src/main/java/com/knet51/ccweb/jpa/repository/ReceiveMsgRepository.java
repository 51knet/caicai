package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
public interface ReceiveMsgRepository extends JpaRepository<ReceiveMsg, Long>, JpaSpecificationExecutor<ReceiveMsg>  {
	
	Page<ReceiveMsg> findReceiveMsgByUserAndReadedAndTypes(User user,Integer isRead, String types, Pageable pageable);
	
//	@Query("select * from ReceiveMsg r where r.types = :types and r.readed = :readed and r.user.id = :userid group by r.commenter order by r.id desc")
//	Page<ReceiveMsg> findReceiveMsg(String types, Integer readed, Long userid,Pageable pageable);

}
