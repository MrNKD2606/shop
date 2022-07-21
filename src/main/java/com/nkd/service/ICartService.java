package com.nkd.service;

import java.util.Set;

import com.nkd.dto.CartDTO;
import com.nkd.dto.OrderDTO;

public interface ICartService {

	Set<OrderDTO> addOrderToSetOrder(OrderDTO order, Set<OrderDTO> list);

	long totalCart(Set<OrderDTO> list);
	
	int countProductToCart(Set<OrderDTO> list);

	void save(CartDTO myCart);
}
