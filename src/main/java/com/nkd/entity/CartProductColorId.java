package com.nkd.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartProductColorId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "cart_id")
	private Long cartId;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "color_id")
	private Long colorId;

	public CartProductColorId() {
	}

	public CartProductColorId(Long productId, Long colorId, Long cartId) {
		this.productId = productId;
		this.colorId = colorId;
		this.cartId = cartId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

}
