package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
public interface ReceiveMsgRepository extends JpaRepository<ReceiveMsg, Long>, JpaSpecificationExecutor<ReceiveMsg>  {
	Page<ReceiveMsg> findReceiveMsgByUserAndReaded(User user,Integer isRead,Pageable pageable);

}
