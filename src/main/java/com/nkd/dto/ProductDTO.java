package com.nkd.dto;

import com.nkd.form.ProductForm;

public class ProductDTO extends AbstractDTO {

	private String masp;
	private String name;
	private String image;
	private Long cost;
	private String shortDescription;
	private CategoryDTO category;

	public ProductDTO() {
	}

	public ProductDTO(ProductForm form) {
		this.masp = form.getMasp();
		this.name = form.getName();
		this.category = new CategoryDTO(form.getCategoryCode());
		this.cost = form.getCost();
		this.shortDescription = form.getShortDescription();
		if (form.getChooseImage() != null) {
			this.image = form.getChooseImage().getOriginalFilename();
		}
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
