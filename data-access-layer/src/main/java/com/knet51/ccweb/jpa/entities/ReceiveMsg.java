package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ReceiveMsg extends AbstractEntity {
	
	private Integer readed; // 1:unReaded; 2:readed; 3:deleted
	private Integer deled;	// useless... just leave it alone
	@ManyToOne
	private User user;
	@ManyToOne
	private SendMsg sendMsg;
	
	private Long commenter;
	private Long commentid;
	
	private String types;// comments or msgs

	public User getUser() {
		return user;
	}
	
	public Long getCommentid() {
		return commentid;
	}

	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}

	public Integer getReaded() {
		return readed;
	}
	public void setReaded(Integer readed) {
		this.readed = readed;
	}
	public Integer getDeled() {
		return deled;
	}
	public void setDeled(Integer deled) {
		this.deled = deled;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SendMsg getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(SendMsg sendMsg) {
		this.sendMsg = sendMsg;
	}
	
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	
	public Long getCommenter() {
		return commenter;
	}

	public void setCommenter(Long commenter) {
		this.commenter = commenter;
	}

	public ReceiveMsg() {
		super();
	}
	
	
}
