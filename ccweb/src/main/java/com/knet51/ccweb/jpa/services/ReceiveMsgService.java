package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.User;

public interface ReceiveMsgService {
	void  add(ReceiveMsg  receiveMsg);
	void del(Long  id);  
	void destory(Long id);  
	void isRead(Long id);
	ReceiveMsg  detail(Long id);
	List<ReceiveMsg>  list(Long userId);
	List<ReceiveMsg>  unReadList(Long userId);
	List<ReceiveMsg> isReadList(Long userId);
	List<ReceiveMsg> isDele(Long userId);
	
	Page<ReceiveMsg> findIsReadMsgByUser(int pageNum, int pageSize, User user,Integer isRead);
}
