package com.nkd.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.nkd.dto.CartDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.CartEntity;

public interface ICartService {

	Set<OrderDTO> addOrderToSetOrder(OrderDTO order, Set<OrderDTO> list);

	long totalCart(Set<OrderDTO> list);
	
	int countProductToCart(Set<OrderDTO> list);

	void save(CartDTO myCart);
	
	List<CartEntity> findAll();
	
	List<CartEntity> findAllByStatus(int status);

	CartEntity findOneByMaCart(String maCart);

	void payCart(CartEntity entity);
	
	List<CartEntity> findAllByCreatedDateBetweenAndStatus(LocalDate start, LocalDate end, int status);
}
