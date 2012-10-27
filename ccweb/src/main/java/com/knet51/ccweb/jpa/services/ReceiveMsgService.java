package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.ReceiveMsg;

public interface ReceiveMsgService {
	void  add(ReceiveMsg  receiveMsg);
	void del(Long  id);  // 1--2 放入回收站 未读时数值为1
	void del2(Long id);  // 2--3 删除，在页面不显示，但是数据库仍然存在
	void isRead(Long id); //初始值1 表示未读，2表示已读
	ReceiveMsg  detail(Long id);
	List<ReceiveMsg>  list(Long userId);//指定user的所有收件
	List<ReceiveMsg>  unReadList(Long userId);//所有未读收件
	List<ReceiveMsg> isReadList(Long userId);//所有已读收件
	List<ReceiveMsg> isDele(Long userId);//垃圾箱所有收件
}
