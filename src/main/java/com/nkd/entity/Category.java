package com.nkd.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "category")
	private List<Product> productes = new ArrayList<>();

//	public Category() {
//	}
//
//	public Category(String code, String name) {
//		this.code = code;
//		this.name = name;
//	}

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

	public List<Product> getProductes() {
		return productes;
	}

	public void setProductes(List<Product> productes) {
		this.productes = productes;
	}

}