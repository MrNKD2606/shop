package com.nkd.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nkd.dto.CartDTO;
import com.nkd.dto.ColorDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.Cart;
import com.nkd.entity.CartProductColor;
import com.nkd.entity.ProductColor;

@Component
public class CartConverter {

	public OrderDTO toOrderDto(ProductColor entity) {
		OrderDTO dto = new OrderDTO();
		dto.setMasp(entity.getProduct().getMasp());
		dto.setName(entity.getProduct().getName());
		dto.setImage(entity.getProduct().getImage());
		dto.setShortDescription(entity.getProduct().getShortDescription());
		dto.setColor(new ColorDTO(entity.getColor().getName(), entity.getColor().getCode()));
		dto.setCost(entity.getProduct().getCost());
		dto.setPercent(entity.getPercent());
		dto.setQuantity(entity.getQuantity());
		return dto;
	}

	public Cart toEntity(CartDTO dto) {
		Cart entity = new Cart();
		entity.setNameCustomer(dto.getName());
		entity.setPhoneCustomer(dto.getPhone());
		entity.setAddressCustomer(dto.getAddress());
		entity.setMaCart(dto.getMaCart());
		
		entity.setNote(dto.getNote());
		return entity;
	}

	public CartDTO toDto(Cart entity) {
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
		for (CartProductColor item : entity.getCartProductColor()) {
			OrderDTO order = new OrderDTO();
			order.setAmount(item.getAmount());
			order.setPercent(item.getPercent());
			order.setCost(item.getCost());
			order.setName(item.getProduct().getName());
			order.setMasp(item.getProduct().getMasp());
			order.setColor(new ColorDTO(item.getColor().getName(), item.getColor().getCode()));
			list.add(order);
		}
		dto.setListOrder(list);
		return dto;
	}

	public List<CartDTO> toListDto(List<Cart> entities) {
		List<CartDTO> result = new ArrayList<>();
		for (Cart item : entities) {
			result.add(toDto(item));
		}
		return result;
	}
}
