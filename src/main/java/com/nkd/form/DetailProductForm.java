package com.nkd.form;

import com.nkd.dto.DetailProductDTO;

public class DetailProductForm extends ProductForm {

	private String colorCode;
	private int quantity;
	private long percent;
	private String categoryName;

	public DetailProductForm() {
	}

	public DetailProductForm(DetailProductDTO dto) {
		super(dto);
		this.categoryName = dto.getCategory().getName();
		if (dto.getColor() != null) {
			this.colorCode = dto.getColor().getCode();
		}
		if (dto.getQuantity() != 0) {
			this.quantity = dto.getQuantity();
		}
		if (dto.getPercent() != 0) {
			this.percent = dto.getPercent();
		}
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
