package com.nkd.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "colors")
public class Color extends BaseEntity {

	private String code;
	private String name;

	@OneToMany(mappedBy = "color")
	private Set<ProductColor> color;

	@OneToMany(mappedBy = "color")
	private Set<CartProductColor> cartColorProduct;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductColor> getColor() {
		return color;
	}

	public void setColor(Set<ProductColor> color) {
		this.color = color;
	}

	public Set<CartProductColor> getCartColorProduct() {
		return cartColorProduct;
	}

	public void setCartColorProduct(Set<CartProductColor> cartColorProduct) {
		this.cartColorProduct = cartColorProduct;
	}

}
