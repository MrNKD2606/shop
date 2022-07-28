package com.nkd.dto;

import java.util.Set;

public class OrderDTO {

	private String masp;
	private String name;
	private String image;
	private String shortDescription;
	private String colorName;
	private String colorCode;
	private Long cost;
	private long percent;
	private int amount;
	private int quantity;

	private Set<ColorDTO> colors;

	public OrderDTO() {
	}
	
	public long getPrice() {
		return cost * (100 + percent) / 100;
	}

	public String getMasp() {
		return masp;
	}

	public void setMasp(String masp) {
		this.masp = masp;
	}

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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public long getPercent() {
		return percent;
	}

	public void setPercent(long percent) {
		this.percent = percent;
	}

	public Set<ColorDTO> getColors() {
		return colors;
	}

	public void setColors(Set<ColorDTO> colors) {
		this.colors = colors;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
