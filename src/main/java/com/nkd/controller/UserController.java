package com.nkd.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nkd.converter.CartConverter;
import com.nkd.converter.ColorConverter;
import com.nkd.dto.CartDTO;
import com.nkd.dto.ColorDTO;
import com.nkd.dto.OrderDTO;
import com.nkd.entity.ProductColorEntity;
import com.nkd.service.ICartService;
import com.nkd.service.IDetailProductService;
import com.nkd.service.IProductService;
import com.nkd.util.Utils;

@Controller
public class UserController {

	@Autowired
	private IDetailProductService detailProductService;

	@Autowired
	private IProductService productService;

	@Autowired
	private ColorConverter colorConverter;

	@Autowired
	private CartConverter cartConverter;

	@Autowired
	private ICartService cartService;

	@GetMapping(value = { "/web", "/web/{categoryCode}" })
	public String web(HttpServletRequest request, Model model, @PathVariable(required = false) String categoryCode) {
		CartDTO myCart = Utils.getCartInSession(request);
		model.addAttribute("cart", myCart);
		if(categoryCode == null) {
			model.addAttribute("products", productService.findAll());
		} else {
			model.addAttribute("products", productService.findAllByCategoryCode(categoryCode));
		}
		return "web";
	}
	
	@GetMapping(value = { "/web/order/{masp}", "/web/order/{masp}/{codeColor}" })
	public String order(HttpServletRequest request, Model model, @PathVariable String masp,
			@PathVariable(required = false) String codeColor) {
		OrderDTO result = new OrderDTO();
		List<ProductColorEntity> list = detailProductService.findAllProductByMasp(masp);
		if (codeColor == null) {
			if (list != null) {
				result = cartConverter.toOrderDto(list.get(0));
			}
		} else {
			result = cartConverter.toOrderDto(detailProductService.findOneByMaspAndCodeColor(masp, codeColor));
		}

		Set<ColorDTO> colors = new HashSet<>();
		for (ProductColorEntity item : list) {
			colors.add(colorConverter.toDto(item.getColor()));
		}
		result.setColors(colors);
		result.setAmount(1);
		model.addAttribute("product", result);
		CartDTO myCart = Utils.getCartInSession(request);
		model.addAttribute("cart", myCart);
		return "order";
	}

	@GetMapping(value = "/web/addOrder/{masp}/{codeColor}")
	public String addOrder(HttpServletRequest request, Model model, @ModelAttribute("product") OrderDTO product,
			@PathVariable String codeColor) {

		ProductColorEntity entity = detailProductService.findOneByMaspAndCodeColor(product.getMasp(), codeColor);
		OrderDTO order = cartConverter.toOrderDto(entity);
		order.setAmount(product.getAmount());

		CartDTO result = Utils.getCartInSession(request);
		Set<OrderDTO> list = result.getListOrder();
		result.setListOrder(cartService.addOrderToSetOrder(order, list));

		model.addAttribute("cart", result);
		model.addAttribute("product", order);
		return "redirect:/web/order/{masp}/{codeColor}";
	}

	@GetMapping(value = "/web/deleteOrder/{masp}/{codeColor}")
	public String deleteOrder(HttpServletRequest request, Model model, @PathVariable String masp,
			@PathVariable String codeColor) {

		CartDTO result = Utils.getCartInSession(request);
		Set<OrderDTO> list = result.getListOrder();
		for (OrderDTO item : list) {
			if (item.getMasp().equals(masp) && item.getColorCode().equals(codeColor)) {
				list.remove(item);
				break;
			}
		}
		result.setListOrder(list);

		model.addAttribute("cart", result);
		return "redirect:/web/cart";
	}

	@GetMapping(value = "/web/editOrder/{masp}/{codeColor}/{edit}")
	public String editOrder(HttpServletRequest request, Model model, @PathVariable String masp,
			@PathVariable String codeColor, @PathVariable String edit) {

		CartDTO result = Utils.getCartInSession(request);
		Set<OrderDTO> list = result.getListOrder();
		for (OrderDTO item : list) {
			if (item.getMasp().equals(masp) && item.getColorCode().equals(codeColor)) {
				if (edit.equals("plus")) {
					item.setAmount(item.getAmount() + 1);
				} else {
					if(item.getAmount() > 1) {
						item.setAmount(item.getAmount() - 1);
					} else {
						
					}
				}
			}
		}
		result.setListOrder(list);

		model.addAttribute("cart", result);
		return "redirect:/web/cart";
	}

	@GetMapping(value = "/web/cart")
	public String cart(HttpServletRequest request, Model model) {
		CartDTO myCart = Utils.getCartInSession(request);
		model.addAttribute("cart", myCart);
		return "cart";
	}

	@PostMapping(value = { "/web/addCart" })
	public String editProduct(HttpServletRequest request, Model model, @ModelAttribute("cart") @Valid CartDTO cart) {
		CartDTO myCart = Utils.getCartInSession(request);
		myCart.setAddress(cart.getAddress());
		myCart.setName(cart.getName());
		myCart.setPhone(cart.getPhone());
		myCart.setNote(cart.getNote());
		cartService.save(myCart);
		
		Utils.removeCartInSession(request);
		return "redirect:/web";
	}

	@RequestMapping(value = "/web/test", method = RequestMethod.GET)
	public String test(Model model) {

		return "test";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/web";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultAfterLogin() {
		Collection<? extends GrantedAuthority> authorities;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		authorities = auth.getAuthorities();
		String myRole = authorities.toArray()[0].toString();
		if (myRole.equals("ROLE_ADMIN")) {
			return "redirect:/admin/index";
		} else if (myRole.equals("ROLE_USER")) {
			return "redirect:/web";
		}
		return "redirect:/web";
	}
}
