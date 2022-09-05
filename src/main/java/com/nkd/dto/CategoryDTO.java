package com.nkd.dto;

public class CategoryDTO extends AbstractDTO {

	private String code;
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(String code) {
		this.code = code;
	}

	public CategoryDTO(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
