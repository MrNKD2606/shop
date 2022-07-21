package com.nkd.dto;

import java.util.Set;

public class DetailProductDTO {

	private String masp;
	private String name;
	private String image;
	private String shortDescription;
	private Long categoryId;
	private String categoryCode;
	private String categoryName;

	private Long cost;
	private long price;
	private long percent;
	private int quantity;
	private ProductColorId id;
	private String color;
	private String colorCode;
	private Set<ColorDTO> colors;
	private Set<ColorDTO> colorsOK;

	public DetailProductDTO() {
	}

	public DetailProductDTO(DetailProductDTO obj) {
		this.masp = obj.getMasp();
		this.name = obj.getName();
		this.image = obj.getImage();
		this.shortDescription = obj.getShortDescription();
		this.categoryId = obj.getCategoryId();
		this.categoryCode = obj.getCategoryCode();
		this.categoryName = obj.getCategoryName();
		this.cost = obj.getCost();
		this.quantity = obj.getQuantity();
		this.color = obj.getColor();
		this.colorCode = obj.getColorCode();
		this.colors = obj.getColors();
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Set<ColorDTO> getColors() {
		return colors;
	}

	public void setColors(Set<ColorDTO> colors) {
		this.colors = colors;
	}

	public Set<ColorDTO> getColorsOK() {
		return colorsOK;
	}

	public void setColorsOK(Set<ColorDTO> colorsOK) {
		this.colorsOK = colorsOK;
	}

	public ProductColorId getId() {
		return id;
	}

	public void setId(ProductColorId id) {
		this.id = id;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getPercent() {
		return percent;
	}

	public void setPercent(long percent) {
		this.percent = percent;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

}