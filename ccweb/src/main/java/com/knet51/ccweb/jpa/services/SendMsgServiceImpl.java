package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.dao.ReceiveMsgDao;
import com.knet51.ccweb.jpa.dao.SendMsgDao;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.SendMsg;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.ReceiveMsgRepository;

@Transactional
@Service("sendMsgService")
public class SendMsgServiceImpl implements SendMsgService {
	
	@Autowired
	private SendMsgDao sendMsgDao;
	
	@Autowired
	private ReceiveMsgDao receiveMsgDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReceiveMsgRepository receiveMsgRepository;

	@Override
	public ReceiveMsg add(SendMsg sendMsg, Long userId ,Long senderid) {
		sendMsgDao.add(sendMsg);
		ReceiveMsg receiveMsg = new ReceiveMsg();
		receiveMsg.setDeled(1);
		receiveMsg.setReaded(1);
		//UserInfo userInfo = new UserInfo();
		User user = userService.findOne(userId);
		//user.setId(userId);
		receiveMsg.setUser(user);
		receiveMsg.setSendMsg(sendMsg);
		receiveMsg.setTypes(GlobalDefs.MSG_TYPES_MESSAGE);
		receiveMsg.setCommenter(senderid);
		//SendMsg newSendMsg = receiveMsgDao.add(receiveMsg);
		return receiveMsgRepository.saveAndFlush(receiveMsg);
		
	}

	@Override
	public void del(Long id) {
		sendMsgDao.del(id);
	}

	@Override
	public SendMsg detail(Long id) {
		return sendMsgDao.detail(id);
	}

	@Override
	public List<SendMsg> list(Long userId) {
		return sendMsgDao.list(userId);
	}

}
