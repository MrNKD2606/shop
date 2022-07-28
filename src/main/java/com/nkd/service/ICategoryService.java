package com.nkd.service;

import java.util.List;

import com.nkd.entity.CategoryEntity;

public interface ICategoryService {
	
	List<CategoryEntity> findAll();
	
	CategoryEntity findOneByCode(String categoryCode);
	
}
