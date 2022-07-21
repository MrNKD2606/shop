package com.nkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.entity.CategoryEntity;
import com.nkd.repository.CategoryRepository;
import com.nkd.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryEntity> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public CategoryEntity findOneByCode(String categoryCode) {
		return categoryRepository.findOneByCode(categoryCode);
	}
}
