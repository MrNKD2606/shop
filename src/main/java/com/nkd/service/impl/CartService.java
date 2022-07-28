package com.nkd.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.converter.CartConverter;
import com.nkd.dto.CartDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.CartEntity;
import com.nkd.entity.CartProductColorEntity;
import com.nkd.entity.CartProductColorId;
import com.nkd.repository.CartProductColorRepository;
import com.nkd.repository.CartRepository;
import com.nkd.service.ICartService;
import com.nkd.service.IColorService;
import com.nkd.service.IProductService;
import com.nkd.util.DateUtils;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartConverter cartConverter;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private CartProductColorRepository cartProductColorRepository;
	
	@Override
	public Set<OrderDTO> addOrderToSetOrder(OrderDTO order, Set<OrderDTO> list) {
		for (OrderDTO item : list) {
			if (item.getColorCode().equals(order.getColorCode()) && item.getMasp().equals(order.getMasp())) {
				order.setAmount(item.getAmount() + order.getAmount());
				list.remove(item);
			}
		}
		list.add(order);
		return list;
	}

	@Override
	public long totalCart(Set<OrderDTO> list) {
		long total = 0;
		for (OrderDTO item : list) {
			total = total + item.getAmount() * item.getPrice();
		}
		return total;
	}

	@Override
	public int countProductToCart(Set<OrderDTO> list) {
		int count = 0;
		for(OrderDTO item : list) {
			count = count + item.getAmount();
		}
		return count;
	}
	
	public CartEntity findOneByMaCart(String masp) {
		return cartRepository.findOneByMaCart(masp);
	}

	@Override
	@Transactional
	public void save(CartDTO myCart) {
		CartEntity cartEntity = new CartEntity();
		cartEntity = cartConverter.toEntity(myCart);
		cartEntity.setStatus(1);
		cartEntity.setMaCart(myCart.getPhone() + String.valueOf(System.currentTimeMillis()));
		cartRepository.save(cartEntity);
		cartEntity.setId(findOneByMaCart(cartEntity.getMaCart()).getId());
		
		List<CartProductColorEntity> list = new ArrayList<>();
		for(OrderDTO item : myCart.getListOrder()) {
			CartProductColorEntity entity = new CartProductColorEntity();
			entity.setPercent(item.getPercent());
			entity.setAmount(item.getAmount());
			entity.setCost(item.getCost());
			entity.setCart(cartEntity);
			entity.setColor(colorService.findOneByCode(item.getColorCode()));
			entity.setProduct(productService.findOneByMasp(item.getMasp()));
			entity.setId(new CartProductColorId(entity.getProduct().getId(), entity.getColor().getId(), entity.getCart().getId()));
			list.add(entity);
		}
		cartProductColorRepository.save(list);
	}

	@Override
	public List<CartEntity> findAll() {
		return cartRepository.findAll();
	}

	@Override
	public List<CartEntity> findAllByStatus(int status) {
		return cartRepository.findAllByStatus(status);
	}

	@Override
	@Transactional
	public void payCart(CartEntity entity) {
		entity.setStatus(0);
		cartRepository.save(entity);
	}

	@Override
	public List<CartEntity> findAllByCreatedDateBetweenAndStatus(LocalDate start, LocalDate end, int status) {
		List<CartEntity> entities = new ArrayList<>();
		Date s = DateUtils.asDate(start);
		Date e = DateUtils.asDate(end.plusDays(1));
		entities = cartRepository.findAllByCreatedDateBetweenAndStatus(s, e, status);
		return entities;
	}

}
