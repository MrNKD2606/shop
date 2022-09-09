package com.nkd.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nkd.dto.CategoryDTO;
import com.nkd.entity.Category;

@Component
public class CategoryConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public CategoryDTO toDto(Category entity) {
//		CategoryDTO dto = new CategoryDTO();
//		dto.setCode(entity.getCode());
//		dto.setName(entity.getName());
//		return dto;
		return modelMapper.map(entity, CategoryDTO.class);
	}
	
	public Set<CategoryDTO> toSetDto(List<Category> entities){
		Set<CategoryDTO> result = new HashSet<>();
		for(Category item : entities) {
			result.add(toDto(item));
		}
		return result;
	}
	
	public List<CategoryDTO> toListDto(List<Category> entities){
		List<CategoryDTO> result = new ArrayList<>();
		for(Category item : entities) {
			result.add(toDto(item));
		}
		return result;
	}

	public Category toEntity(CategoryDTO dto) {
		Category entity = new Category();
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
		return entity;
	}

	public Category toEntity(CategoryDTO dto, Category oldEntity) {
		oldEntity.setCode(dto.getCode());
		oldEntity.setName(dto.getName());
		return oldEntity;
	}
}
