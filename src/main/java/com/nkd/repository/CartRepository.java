package com.nkd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findOneByMaCart(String macart);

	List<Cart> findAllByStatus(int status);

	List<Cart> findAllByCreatedDateBetweenAndStatus(Date start, Date end, int status);

}
