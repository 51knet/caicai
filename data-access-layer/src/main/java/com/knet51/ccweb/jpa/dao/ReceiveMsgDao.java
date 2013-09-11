package com.knet51.ccweb.jpa.dao;

import java.util.List;

import com.knet51.ccweb.jpa.entities.ReceiveMsg;

public interface ReceiveMsgDao {
	void  add(ReceiveMsg  receiveMsg);
	void del(Long  id);  
	void destory(Long id);  
	void isRead(Long id); 
	ReceiveMsg  detail(Long id);
	List<ReceiveMsg>  list(Long userId);
	List<ReceiveMsg>  unReadList(Long userId);
	List<ReceiveMsg> isReadList(Long userId);
	List<ReceiveMsg> isDele(Long userId);
	
	List<ReceiveMsg> unReadMsgSenderList(Long userId, String types);
	
	List<ReceiveMsg> showMsgByUsers(Long user_id ,Long sender_id, String types);
}
