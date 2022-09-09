package com.nkd.service;

import java.util.List;

import com.nkd.entity.Category;

public interface ICategoryService {
	
	List<Category> findAll();
	
	Category findOneByCode(String categoryCode);

	Category save(Category dto);

	void delete(String code);
	
}
