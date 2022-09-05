package com.nkd.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productes")
public class Product extends BaseEntity {

	@Column(name = "masp")
	private String masp;

	@Column(name = "name")
	private String name;

	@Column(name = "image")
	private String image;

	@Column(name = "price")
	private Long cost;

	@Column(name = "shortdescription")
	private String shortDescription;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "product")
	private Set<ProductColor> productColor;

	@OneToMany(mappedBy = "product")
	private Set<CartProductColor> cartProductColor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<ProductColor> getproductColor() {
		return productColor;
	}

	public void setproductColor(Set<ProductColor> price) {
		this.productColor = price;
	}

	public String getMasp() {
		return masp;
	}

	public void setMasp(String masp) {
		this.masp = masp;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Set<ProductColor> getProductColor() {
		return productColor;
	}

	public void setProductColor(Set<ProductColor> productColor) {
		this.productColor = productColor;
	}

	public Set<CartProductColor> getCartProductColor() {
		return cartProductColor;
	}

	public void setCartProductColor(Set<CartProductColor> cartProductColor) {
		this.cartProductColor = cartProductColor;
	}

}