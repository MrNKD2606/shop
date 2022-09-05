package com.nkd.dto;

import com.nkd.form.DetailProductForm;

public class DetailProductDTO extends ProductDTO {

	private long percent;
	private int quantity;

	private ColorDTO color;

	public DetailProductDTO() {
	}
	
	public DetailProductDTO(DetailProductForm product) {
		super.setMasp(product.getMasp());
		this.percent = product.getPercent();
		this.quantity = product.getQuantity();
		this.color = new ColorDTO(product.getColorCode());
	}

	public long getPrice() {
		return super.getCost() * (100 + percent) / 100;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPercent() {
		return percent;
	}

	public void setPercent(long percent) {
		this.percent = percent;
	}

	public ColorDTO getColor() {
		return color;
	}

	public void setColor(ColorDTO color) {
		this.color = color;
	}

}
