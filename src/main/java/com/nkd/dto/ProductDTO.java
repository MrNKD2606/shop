package com.nkd.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ProductDTO extends AbstractDTO<ProductDTO> {

	@NotEmpty(message = "Masp NotEmpty")
	private String masp;

	@NotNull(message = "Tên NotNull")
	@Size(min = 2, max = 10, message = "ít hơn 2 hoặc nhiều hơn 10 ký tự")
	private String name;

	// private String image;

	private MultipartFile image;

	private Long cost;
	private String shortDescription;
	private Long categoryId;

	private String categoryCode;
	private String categoryName;

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

//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}

	public String getShortDescription() {
		return shortDescription;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
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

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

}
