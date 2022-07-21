package com.nkd.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductDTO extends AbstractDTO<ProductDTO> {

	@NotEmpty(message = "Masp NotEmpty")
	private String masp;

	@NotNull(message = "Tên NotNull")
	@Size(min = 2, max = 10, message = "ít hơn 2 hoặc nhiều hơn 10 ký tự")
	private String name;

	private String image;
	private String shortDescription;
	private Long cost;

	private Long categoryId;
	private String categoryCode;
	private String categoryName;
	private Set<CategoryDTO> categories;

	public ProductDTO() {
	}

	public ProductDTO(ProductDTO obj) {
		this.masp = obj.masp;
		this.name = obj.name;
		this.image = obj.image;
		this.shortDescription = obj.shortDescription;
		this.categoryId = obj.categoryId;
		this.categoryCode = obj.categoryCode;
		this.categoryName = obj.categoryName;
		this.categories = obj.categories;
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

	public Set<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryDTO> categories) {
		this.categories = categories;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

}
