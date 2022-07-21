package com.nkd.converter;

import org.springframework.stereotype.Component;

import com.nkd.dto.CartDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.CartEntity;
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
		dto.setPrice(entity.getProduct().getCost() + (entity.getPercent() * entity.getProduct().getCost() / 100));
		return dto;
	}
	
	public CartEntity toEntity(CartDTO dto) {
		CartEntity entity = new CartEntity();
		entity.setNameCustomer(dto.getName());
		entity.setPhoneCustomer(dto.getPhone());
		entity.setAddressCustomer(dto.getAddress());
		entity.setNote(dto.getNote());
		return entity;
	}
}
