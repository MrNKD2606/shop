package com.nkd.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nkd.converter.CartConverter;
import com.nkd.dto.CartDTO;
import com.nkd.entity.Cart;
import com.nkd.service.ICartService;

@RestController
@RequestMapping("/api/cart")
public class CartAPI {
	
	@Autowired
	private CartConverter cartConverter;
	
	@Autowired
	private ICartService cartService;

	@GetMapping
	public List<CartDTO> findAll(){
		return cartConverter.toListDto(cartService.findAll());
	}
	
	@GetMapping("/{maCart}")
	public CartDTO findByMaCart(@PathVariable String maCart) {
		return cartConverter.toDto(cartService.findOneByMaCart(maCart));
	}
	
	@Transactional
	@PostMapping
	public CartDTO create(@RequestBody CartDTO cartDto) {
		return cartService.saveAPI(cartDto);
	}
	
	@Transactional
	@PutMapping("/{maCart}")
	public CartDTO update(@PathVariable String maCart, @RequestBody CartDTO cartDto) {
		Cart entity = cartService.findOneByMaCart(maCart);
		if (entity.getStatus() == 1) {
			return cartService.updateAPI(cartConverter.toEntity(cartDto, entity));
		} else {
			return null;
		}
	}
	
	@Transactional
	@PutMapping("/pay/{maCart}")
	public CartDTO pay(@PathVariable String maCart, BindingResult bindingResult) {
		Cart entity = cartService.findOneByMaCart(maCart);
		if (entity.getStatus() == 1) {
			entity.setStatus(0);
			return cartService.updateAPI(entity);
		} else {
			//Ném ra ngoại lệ
			ObjectError error = new ObjectError("globalError", "Status khác 1");
			bindingResult.addError(error);
			return null;
		}
	}
	
	@Transactional
	@PutMapping("/cancel/{maCart}")
	public CartDTO cancel(@PathVariable String maCart) {
		Cart entity = cartService.findOneByMaCart(maCart);
		if (entity.getStatus() == 1) {
			entity.setStatus(2);
			return cartService.updateAPI(entity);
		} else {
			return null;
		}
	}
	
}
