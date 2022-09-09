package com.nkd.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.nkd.dto.CartDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.Cart;

public interface ICartService {

	Set<OrderDTO> addOrderToSetOrder(OrderDTO order, Set<OrderDTO> list);

	long totalCart(Set<OrderDTO> list);
	
	int countProductToCart(Set<OrderDTO> list);

	CartDTO save(CartDTO myCart);
	
	List<Cart> findAll();
	
	List<Cart> findAllByStatus(int status);

	Cart findOneByMaCart(String maCart);

	void payCart(Cart entity);
	
	List<Cart> findAllByCreatedDateBetweenAndStatus(LocalDate start, LocalDate end, int status);

	CartDTO saveAPI(CartDTO cartDto);

	CartDTO updateAPI(Cart entity);
}
