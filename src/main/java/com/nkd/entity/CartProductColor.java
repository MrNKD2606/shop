package com.nkd.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "carts_productes_colors")
public class CartProductColor {

	@EmbeddedId
	private CartProductColorId id;

	@ManyToOne
	@MapsId("cartId")
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@MapsId("colorId")
	@JoinColumn(name = "color_id")
	private Color color;

	private Long percent;

	private int amount;
	
	private Long cost;

	public CartProductColorId getId() {
		return id;
	}

	public void setId(CartProductColorId id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Long getPercent() {
		return percent;
	}

	public void setPercent(Long percent) {
		this.percent = percent;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

}
