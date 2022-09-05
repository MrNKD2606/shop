package com.nkd.dto;

public class ColorDTO extends AbstractDTO {

	private String name;
	private String code;

	public ColorDTO() {
	}

	public ColorDTO(String code) {
		this.code = code;
	}

	public ColorDTO(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
