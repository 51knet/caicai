package com.knet51.ccweb.jpa.repository;

import java.util.List;

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
	
	@Query("select r from ReceiveMsg r where r.types = ?1 and r.readed < ?2 and r.user.id = ?3  group by r.commenter  ")
	Page<ReceiveMsg> findReceiveMsg(String types, Integer readed, Long userid, Pageable pageable);
	
	@Query("select r from ReceiveMsg r where r.types = ?1 and r.readed < ?2 and r.user.id = ?3  and r.commenter= ?4  ")
	List<ReceiveMsg> findCommenterMsgList(String types, Integer readed, Long userid,Long commenterid);
	
	@Query("select r from ReceiveMsg r where r.types = ?1 and r.readed < ?2 and r.user.id = ?3    ")
	List<ReceiveMsg> findUnReadMsgList(String types, Integer readed, Long userid);
	
	@Query("select r from ReceiveMsg r where r.commenter in (?1, ?2) and r.user.id in (?1, ?2) and r.types = ?3")
	Page<ReceiveMsg> findMsgByUsers(Long userid,Long commenterid,String types, Pageable pageable);
	@Query("select r from ReceiveMsg r where r.commenter in (?1, ?2) and r.user.id in (?1, ?2) and r.types = ?3")
	List<ReceiveMsg> findMsgListByUsers(Long userid,Long commenterid,String types);

}
