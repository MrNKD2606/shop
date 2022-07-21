package com.nkd.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "colors")
public class ColorEntity extends BaseEntity {

	private String code;
	private String name;

	@OneToMany(mappedBy = "color")
	private Set<ProductColorEntity> color;

	@OneToMany(mappedBy = "color")
	private Set<CartProductColorEntity> cartColorProduct;

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

	public Set<ProductColorEntity> getColor() {
		return color;
	}

	public void setColor(Set<ProductColorEntity> color) {
		this.color = color;
	}

	public Set<CartProductColorEntity> getCartColorProduct() {
		return cartColorProduct;
	}

	public void setCartColorProduct(Set<CartProductColorEntity> cartColorProduct) {
		this.cartColorProduct = cartColorProduct;
	}

}
