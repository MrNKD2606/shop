package com.nkd.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nkd.dto.CartDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.CartEntity;
import com.nkd.entity.CartProductColorEntity;
import com.nkd.entity.ProductColorEntity;

@Component
public class CartConverter {

	public OrderDTO toOrderDto(ProductColorEntity entity) {
		OrderDTO dto = new OrderDTO();
		dto.setMasp(entity.getProduct().getMasp());
		dto.setName(entity.getProduct().getName());
		dto.setImage(entity.getProduct().getImage());
		dto.setShortDescription(entity.getProduct().getShortDescription());
		dto.setColorName(entity.getColor().getName());
		dto.setColorCode(entity.getColor().getCode());
		dto.setCost(entity.getProduct().getCost());
		dto.setPercent(entity.getPercent());
		dto.setQuantity(entity.getQuantity());
		dto.setImage(entity.getProduct().getImage());
		return dto;
	}

	public CartEntity toEntity(CartDTO dto) {
		CartEntity entity = new CartEntity();
		entity.setNameCustomer(dto.getName());
		entity.setPhoneCustomer(dto.getPhone());
		entity.setAddressCustomer(dto.getAddress());
		entity.setMaCart(dto.getMaCart());
		
		entity.setNote(dto.getNote());
		return entity;
	}

	public CartDTO toDto(CartEntity entity) {
		CartDTO dto = new CartDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getNameCustomer());
		dto.setPhone(entity.getPhoneCustomer());
		dto.setAddress(entity.getAddressCustomer());
		dto.setNote(entity.getNote());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setStatus(entity.getStatus());
		dto.setMaCart(entity.getMaCart());
		Set<OrderDTO> list = new HashSet<>();
		for (CartProductColorEntity item : entity.getCartProductColor()) {
			OrderDTO order = new OrderDTO();
			order.setAmount(item.getAmount());
			order.setPercent(item.getPercent());
			order.setCost(item.getCost());
			order.setName(item.getProduct().getName());
			order.setMasp(item.getProduct().getMasp());
			order.setColorName(item.getColor().getName());
			order.setColorCode(item.getColor().getCode());
			list.add(order);
		}
		dto.setListOrder(list);
		return dto;
	}

	public List<CartDTO> toListDto(List<CartEntity> entities) {
		List<CartDTO> result = new ArrayList<>();
		for (CartEntity item : entities) {
			result.add(toDto(item));
		}
		return result;
	}
}
