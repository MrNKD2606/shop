package com.nkd.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nkd.dto.CategoryDTO;
import com.nkd.entity.Category;

@Component
public class CategoryConverter {

	public CategoryDTO toDto(Category entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		return dto;
	}
	
	public Set<CategoryDTO> toSetDto(List<Category> entities){
		Set<CategoryDTO> result = new HashSet<>();
		for(Category item : entities) {
			result.add(toDto(item));
		}
		return result;
	}
}
