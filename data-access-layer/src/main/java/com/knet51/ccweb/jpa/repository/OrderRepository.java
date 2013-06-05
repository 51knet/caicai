package com.knet51.ccweb.jpa.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
public interface OrderRepository  extends JpaRepository<UserOrder, Long>{
	Page<UserOrder> findAll(Pageable pageable);
	Page<UserOrder> findOrderByUser(User user, Pageable pageable);
	List<UserOrder> findOrderByUserAndId(User user, Long id);
}
