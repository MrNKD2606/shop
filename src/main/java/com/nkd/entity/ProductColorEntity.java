package com.nkd.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "productes_colors")
public class ProductColorEntity {

	@EmbeddedId
	private ProductColorId id;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@ManyToOne
	@MapsId("colorId")
	@JoinColumn(name = "color_id")
	private ColorEntity color;

	private Long percent;

	private int quantity;

	public ProductColorId getId() {
		return id;
	}

	public void setId(ProductColorId id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public ColorEntity getColor() {
		return color;
	}

	public void setColor(ColorEntity color) {
		this.color = color;
	}

	public Long getPercent() {
		return percent;
	}

	public void setPercent(Long percent) {
		this.percent = percent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
