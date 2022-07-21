package com.nkd.util;

import javax.servlet.http.HttpServletRequest;

import com.nkd.dto.CartDTO;

public class Utils {

	// Thông tin các sản phẩm trong giỏ hàng, được lưu trữ trong Session.
	public static CartDTO getCartInSession(HttpServletRequest request) {

		CartDTO cartDTO = (CartDTO) request.getSession().getAttribute("cart");

		if (cartDTO == null) {
			cartDTO = new CartDTO();
			request.getSession().setAttribute("cart", cartDTO);
		}

		return cartDTO;
	}

	public static void removeCartInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("cart");
	}
}
