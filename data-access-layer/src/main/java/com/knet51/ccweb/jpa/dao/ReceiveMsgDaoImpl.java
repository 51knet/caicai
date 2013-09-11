package com.knet51.ccweb.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.ReceiveMsg;

@Repository("receiveMsgDao")
public class ReceiveMsgDaoImpl implements ReceiveMsgDao {
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public void add(ReceiveMsg receiveMsg) {
		em.persist(receiveMsg);
	}
	
	
	@Override
	public void del(Long mId) {
		ReceiveMsg receiveMsg = em.find(ReceiveMsg.class, mId);
		//System.out.println("##############"+receiveMsg.getUser()+"################");
		//System.out.println("###############"+mId+"################");
		receiveMsg.setReaded(3);
		em.merge(receiveMsg);
	}
	
	@Override
	public void destory(Long mId) {
		ReceiveMsg receiveMsg = em.find(ReceiveMsg.class, mId);
		receiveMsg.setReaded(4);
		em.merge(receiveMsg);
	}

	@Override
	public ReceiveMsg detail(Long userId) {
		return em.find(ReceiveMsg.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveMsg> list(Long userId) {
		return em.createQuery("from ReceiveMsg where readed<4 and user_id="+userId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveMsg> unReadList(Long userId) {
		return em.createQuery("from ReceiveMsg where readed=1 and user_id="+userId).getResultList();
	}

	@Override
	public void isRead(Long id) {
		ReceiveMsg receiveMsg = em.find(ReceiveMsg.class, id);
		receiveMsg.setReaded(2);
		em.merge(receiveMsg);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveMsg> isReadList(Long userId) {
		return em.createQuery("from ReceiveMsg where readed=2 and user_id="+userId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveMsg> isDele(Long userId) {
		return em.createQuery("from ReceiveMsg where readed=3 and user_id="+userId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveMsg> unReadMsgSenderList(Long userId , String types) {
		Sort sort = new Sort(Direction.DESC, "id"); 
		List<ReceiveMsg> list = em.createQuery("from ReceiveMsg re where re.id in (select r.id from ReceiveMsg r where r.readed <3 and r.user.id="+userId+" and r.types = '"+types+"' group by r.commenter ) order by re.id desc").getResultList();
		return list;
	}


	@Override
	public List<ReceiveMsg> showMsgByUsers(Long user_id, Long sender_id,
			String types) {
		// TODO Auto-generated method stub
		return null;
	}

}
