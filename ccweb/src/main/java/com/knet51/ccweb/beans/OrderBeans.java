package com.knet51.ccweb.beans;

import com.knet51.ccweb.jpa.entities.Order;
import com.knet51.ccweb.jpa.entities.User;

public class OrderBeans {
	private User user;
	private Order order;

	public OrderBeans() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
	
}
