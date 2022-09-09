package com.nkd.dto;

public class OrderDTO extends ProductColorDTO {

	private int amount;
	private Long cost;

	public OrderDTO() {
	}

	public long getPrice() {
		return this.cost * (100 + super.getPercent()) / 100;
		// return super.getCost() * (100 + super.getPercent()) / 100;
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
