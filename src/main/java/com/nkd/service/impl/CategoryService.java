package com.nkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.entity.Category;
import com.nkd.repository.CategoryRepository;
import com.nkd.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findOneByCode(String categoryCode) {
		return categoryRepository.findOneByCode(categoryCode);
	}
}
