package com.nkd.dto;

public class ProductColorId {

	private Long colorId;
	private Long productId;

	
	public ProductColorId() {
	}

	public ProductColorId(Long colorId, Long productId) {
		this.colorId = colorId;
		this.productId = productId;
	}

	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
