package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.User;

public interface ReceiveMsgService {
	void  add(ReceiveMsg  receiveMsg);
	void del(Long  id);  // readed变为3，放入回收站
	void destory(Long id);  // 彻底删除
	void isRead(Long id); //初始值1 表示未读，2表示已读
	ReceiveMsg  detail(Long id);
	List<ReceiveMsg>  list(Long userId);//指定user的所有收件
	List<ReceiveMsg>  unReadList(Long userId);//所有未读收件
	List<ReceiveMsg> isReadList(Long userId);//所有已读收件
	List<ReceiveMsg> isDele(Long userId);//垃圾箱所有收件
	
	Page<ReceiveMsg> findIsReadMsgByUser(int pageNum, int pageSize, User user,Integer isRead);
}
