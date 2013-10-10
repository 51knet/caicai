package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.ReceiveMsgDao;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.ReceiveMsgRepository;

@Transactional
@Service("receiveMsgService")
public class ReceiveMsgServiceImpl implements ReceiveMsgService {

	@Autowired
	private ReceiveMsgDao receiveMsgDao;
	
	@Autowired
	private ReceiveMsgRepository receiveMsgRepository;
	
	@Override
	public void add(ReceiveMsg receiveMsg) {
		receiveMsgDao.add(receiveMsg);
	}

	@Override
	public void del(Long id) {
		receiveMsgDao.del(id);
	}

	@Override
	public void destory(Long id) {
		receiveMsgDao.destory(id);
	}

	@Override
	public void isRead(Long id) {
		receiveMsgDao.isRead(id);
	}

	@Override
	public ReceiveMsg detail(Long id) {
		return receiveMsgDao.detail(id);
	}

	@Override
	public List<ReceiveMsg> list(Long userId) {
		return receiveMsgDao.list(userId);
	}

	@Override
	public List<ReceiveMsg> unReadList(Long userId) {
		return receiveMsgDao.unReadList(userId);
	}

	@Override
	public List<ReceiveMsg> isReadList(Long userId) {
		return receiveMsgDao.isReadList(userId);
	}

	@Override
	public List<ReceiveMsg> isDele(Long userId) {
		return receiveMsgDao.isDele(userId);
	}

	@Override
	public Page<ReceiveMsg> findReceiveMsgByUserAndTypes(int pageNum, int pageSize,
			Long userId, Integer isRead,String types) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<ReceiveMsg> onePage = receiveMsgRepository.findReceiveMsgByUserAndReadedAndTypes(userId, isRead, types, dateDesc);
		return onePage;
	}

	@Override
	public List<ReceiveMsg> unReadMsgSenderListGroup(Long userId , String types) {
		return receiveMsgDao.unReadMsgSenderListGroup(userId, types);
	}


	@Override
	public Page<ReceiveMsg> findReceiveMsgByUserAndReadedAndTypesGroup(int pageNum,
			int pageSize, String types, Integer readed, Long userid) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<ReceiveMsg> onePage = receiveMsgRepository.findReceiveMsgGroup(types, readed, userid, dateDesc);
		return onePage;
	}

	@Override
	public Page<ReceiveMsg> findMsgByUserAndCommenter(int pageNum,
			int pageSize, Long user_id, String types, Long commenter_id) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<ReceiveMsg> onePage = receiveMsgRepository.findMsgByUsers(user_id, commenter_id, types, dateDesc);
		return onePage;
	}

	@Override
	public List<ReceiveMsg> findMsgListByUserAndCommenter(Long user_id,
			String types, Long commenter_id) {
		return receiveMsgRepository.findMsgListByUsers(user_id, commenter_id, types);
	}

	@Override
	public List<ReceiveMsg> findCommenterMsgList(String types, Integer readed,
			Long userid, Long commenterid) {
		return receiveMsgRepository.findCommenterMsgList(types, readed, userid, commenterid);
	}

	@Override
	public List<ReceiveMsg> unReadMsgList(Long userId, String types, Integer readed) {
		Sort sort = new Sort(Direction.DESC, "id"); 
		return receiveMsgRepository.findUnReadMsgList(types, readed, userId,sort);
	}

	@Override
	public Page<ReceiveMsg> findAllByUserAndReadedAndTypes(int pageNum, int pageSize,
			User user, Integer isRead, String types) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<ReceiveMsg> onePage = receiveMsgRepository.findAllByUserAndReaded(user, isRead, dateDesc);
		return onePage;
	}

}
