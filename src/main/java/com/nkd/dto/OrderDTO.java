package com.nkd.dto;

public class OrderDTO extends DetailProductDTO {

	private int amount;

	public OrderDTO() {
	}

	public long getPrice() {
		return super.getCost() * (100 + super.getPercent()) / 100;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
