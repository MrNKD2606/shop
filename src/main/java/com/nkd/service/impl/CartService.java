package com.nkd.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.converter.CartConverter;
import com.nkd.dto.CartDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.Cart;
import com.nkd.entity.CartProductColor;
import com.nkd.entity.CartProductColorId;
import com.nkd.entity.ProductColor;
import com.nkd.repository.CartProductColorRepository;
import com.nkd.repository.CartRepository;
import com.nkd.service.ICartService;
import com.nkd.service.IColorService;
import com.nkd.service.IProductColorService;
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
	private IProductColorService productColorService;

	@Autowired
	private CartProductColorRepository cartProductColorRepository;

	@Override
	public Set<OrderDTO> addOrderToSetOrder(OrderDTO order, Set<OrderDTO> list) {
		for (OrderDTO item : list) {
			if (item.getColor().getCode().equals(order.getColor().getCode())
					&& item.getProduct().getMasp().equals(order.getProduct().getMasp())) {
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
		for (OrderDTO item : list) {
			count = count + item.getAmount();
		}
		return count;
	}

	public Cart findOneByMaCart(String masp) {
		return cartRepository.findOneByMaCart(masp);
	}

	@Override
	@Transactional
	public CartDTO save(CartDTO myCart) {
		Cart cartEntity = new Cart();
		cartEntity = cartConverter.toEntity(myCart);
		cartEntity.setStatus(1);
		cartEntity.setMaCart(myCart.getPhoneCustomer() + String.valueOf(System.currentTimeMillis()));
		cartRepository.save(cartEntity);
		cartEntity.setId(findOneByMaCart(cartEntity.getMaCart()).getId());

		Set<CartProductColor> list = new HashSet<>();
		for (OrderDTO item : myCart.getListOrder()) {
			CartProductColor entity = new CartProductColor();
			entity.setPercent(item.getPercent());
			entity.setAmount(item.getAmount());
			entity.setCost(item.getProduct().getCost());
			entity.setCart(cartEntity);
			entity.setColor(colorService.findOneByCode(item.getColor().getCode()));
			entity.setProduct(productService.findOneByMasp(item.getProduct().getMasp()));
			entity.setId(new CartProductColorId(entity.getProduct().getId(), entity.getColor().getId(),
					entity.getCart().getId()));
			list.add(entity);
		}
		cartProductColorRepository.save(list);
		cartEntity.setCartProductColor(list);
		return cartConverter.toDto(cartEntity);
	}

	@Transactional
	@Override
	public CartDTO saveAPI(CartDTO cartDto) {
		Cart cartEntity = cartConverter.toEntity(cartDto);
		cartEntity.setStatus(1);
		cartEntity.setMaCart(cartDto.getPhoneCustomer() + String.valueOf(System.currentTimeMillis()));
		cartRepository.save(cartEntity);
		cartEntity.setId(findOneByMaCart(cartEntity.getMaCart()).getId());
		
		Set<CartProductColor> list = new HashSet<>();
		for (OrderDTO item : cartDto.getListOrder()) {
			ProductColor productColor = productColorService.findOneByMaspAndCodeColor(item.getProduct().getMasp(), item.getColor().getCode());
			CartProductColor entity = new CartProductColor();
			entity.setPercent(productColor.getPercent());
			entity.setAmount(item.getAmount());
			entity.setCost(productColor.getProduct().getCost());
			entity.setCart(cartEntity);
			entity.setColor(productColor.getColor());
			entity.setProduct(productColor.getProduct());
			entity.setId(new CartProductColorId(entity.getProduct().getId(), entity.getColor().getId(), entity.getCart().getId()));
			list.add(entity);
		}
		cartProductColorRepository.save(list);
		cartEntity.setCartProductColor(list);
		return cartConverter.toDto(cartEntity);
	}
	
	@Transactional
	@Override
	public CartDTO updateAPI(Cart entity) {
		cartRepository.save(entity);
		return cartConverter.toDto(findOneByMaCart(entity.getMaCart()));
	}

	@Override
	public List<Cart> findAll() {
		return cartRepository.findAll();
	}

	@Override
	public List<Cart> findAllByStatus(int status) {
		return cartRepository.findAllByStatus(status);
	}

	@Override
	@Transactional
	public void payCart(Cart entity) {
		entity.setStatus(0);
		cartRepository.save(entity);
	}

	@Override
	public List<Cart> findAllByCreatedDateBetweenAndStatus(LocalDate start, LocalDate end, int status) {
		List<Cart> entities = new ArrayList<>();
		Date s = DateUtils.asDate(start);
		Date e = DateUtils.asDate(end.plusDays(1));
		entities = cartRepository.findAllByCreatedDateBetweenAndStatus(s, e, status);
		return entities;
	}

	

}
