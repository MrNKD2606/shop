package com.nkd.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.nkd.dto.ProductDTO;

public class ProductForm {

	@NotEmpty(message = "Masp NotEmpty")
	private String masp;
	
	@NotNull(message = "Tên NotNull")
	@Size(min = 2, max = 10, message = "ít hơn 2 hoặc nhiều hơn 10 ký tự")
	private String name;
	
	private String categoryCode;
	private Long cost;
	private String shortDescription;
	private String image;
	private MultipartFile chooseImage;

	public ProductForm() {
	}

	public ProductForm(ProductDTO dto) {
		this.masp = dto.getMasp();
		this.name = dto.getName();
		this.categoryCode = dto.getCategory().getCode();
		this.cost = dto.getCost();
		this.shortDescription = dto.getShortDescription();
		this.image = dto.getImage();
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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public MultipartFile getChooseImage() {
		return chooseImage;
	}

	public void setChooseImage(MultipartFile chooseImage) {
		this.chooseImage = chooseImage;
	}

}
