package com.nkd.converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nkd.dto.CartDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.Cart;
import com.nkd.entity.CartProductColor;
import com.nkd.entity.ProductColor;

@Component
public class CartConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public OrderDTO toOrderDto(ProductColor entity) {
//		OrderDTO dto = new OrderDTO();
//		dto.setMasp(entity.getProduct().getMasp());
//		dto.setName(entity.getProduct().getName());
//		dto.setImage(entity.getProduct().getImage());
//		dto.setShortDescription(entity.getProduct().getShortDescription());
//		dto.setColor(new ColorDTO(entity.getColor().getName(), entity.getColor().getCode()));
//		dto.setCost(entity.getProduct().getCost());
//		dto.setPercent(entity.getPercent());
//		dto.setQuantity(entity.getQuantity());
//		return dto;
		return null;
	}

	public Cart toEntity(CartDTO dto) {
		Cart entity = new Cart();
		entity.setNameCustomer(dto.getNameCustomer());
		entity.setPhoneCustomer(dto.getPhoneCustomer());
		entity.setAddressCustomer(dto.getAddressCustomer());
		entity.setMaCart(dto.getMaCart());
		entity.setNote(dto.getNote());
		return entity;
	}

	public CartDTO toDto(Cart entity) {
//		CartDTO dto = new CartDTO();
//		dto.setId(entity.getId());
//		dto.setNameCustomer(entity.getNameCustomer());
//		dto.setPhoneCustomer(entity.getPhoneCustomer());
//		dto.setAddressCustomer(entity.getAddressCustomer());
//		dto.setNote(entity.getNote());
//		dto.setCreatedDate(entity.getCreatedDate());
//		dto.setStatus(entity.getStatus());
//		dto.setMaCart(entity.getMaCart());
//		Set<OrderDTO> list = new HashSet<>();
//		for (CartProductColor item : entity.getCartProductColor()) {
//			OrderDTO order = new OrderDTO();
//			order.setAmount(item.getAmount());
//			order.setPercent(item.getPercent());
//			order.setCost(item.getCost());
//			order.setName(item.getProduct().getName());
//			order.setMasp(item.getProduct().getMasp());
//			order.setColor(new ColorDTO(item.getColor().getName(), item.getColor().getCode()));
//			list.add(order);
//		}
//		dto.setListOrder(list);
//		return dto;
		
		Converter<Set<CartProductColor>, Set<OrderDTO>> converterCartProductColor = c -> c.getSource().size() == 0 ? null
				: c.getSource().stream().map(item -> modelMapper.map(item, OrderDTO.class)).collect(Collectors.toSet());
		this.modelMapper.typeMap(Cart.class, CartDTO.class).addMappings(mapper -> {
			mapper.using(converterCartProductColor).map(Cart::getCartProductColor, CartDTO::setListOrder);
		});
		return this.modelMapper.map(entity, CartDTO.class);
	}

	public List<CartDTO> toListDto(List<Cart> entities) {
//		List<CartDTO> result = new ArrayList<>();
//		for (Cart item : entities) {
//			result.add(toDto(item));
//		}
//		return result;
		Converter<Set<CartProductColor>, Set<OrderDTO>> converterCartProductColor = c -> c.getSource().size() == 0 ? null
				: c.getSource().stream().map(item -> modelMapper.map(item, OrderDTO.class)).collect(Collectors.toSet());
		this.modelMapper.typeMap(Cart.class, CartDTO.class).addMappings(mapper -> {
			mapper.using(converterCartProductColor).map(Cart::getCartProductColor, CartDTO::setListOrder);
		});
		return entities.stream().map(item -> modelMapper.map(item, CartDTO.class)).collect(Collectors.toList());
	}

	public Cart toEntity(CartDTO cartDto, Cart entity) {
		entity.setNameCustomer(cartDto.getNameCustomer());
		entity.setPhoneCustomer(cartDto.getPhoneCustomer());
		entity.setAddressCustomer(cartDto.getAddressCustomer());
		entity.setNote(cartDto.getNote());
		return entity;
	}
}
