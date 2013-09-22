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
	
	Page<ReceiveMsg> findReceiveMsgByUserAndTypes(int pageNum, int pageSize, User user,Integer isRead, String types);
	List<ReceiveMsg> unReadMsgSenderList(Long userId , String types); 
	
	Page<ReceiveMsg> findReceiveMsgByUserAndReadedAndTypes(int pageNum, int pageSize, String types, Integer readed, Long userid);
	List<ReceiveMsg> findCommenterMsgList(String types, Integer readed, Long userid,Long commenterid);
	
	Page<ReceiveMsg> findMsgByUserAndCommenter(int pageNum, int pageSize ,Long user_id, String types,Long commenter_id);
	List<ReceiveMsg> findMsgListByUserAndCommenter(Long user_id, String types,Long commenter_id);
	
}
