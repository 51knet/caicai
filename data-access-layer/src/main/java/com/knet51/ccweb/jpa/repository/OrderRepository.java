package com.knet51.ccweb.jpa.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.Order;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
public interface OrderRepository  extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>{
	
	Page<Order> findOrderByUser(User user, Pageable pageable);
	List<Order> findOrderByUserAndId(User user, Long id);
	Page<Order> findByUser(User user, Pageable pageable);
}
