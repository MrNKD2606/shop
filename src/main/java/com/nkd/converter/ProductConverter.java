package com.nkd.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nkd.dto.ProductDTO;
import com.nkd.entity.ProductEntity;
import com.nkd.service.ICategoryService;

@Component
public class ProductConverter {
	
	@Autowired
	private ICategoryService categoryService;
	
	public ProductDTO toDto(ProductEntity entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setMasp(entity.getMasp());
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
		dto.setCost(entity.getCost());
		dto.setShortDescription(entity.getShortDescription());
		dto.setCategoryCode(entity.getCategory().getCode());
		dto.setCategoryName(entity.getCategory().getName());
		return dto;
	}
	
	public List<ProductDTO> toListDto(List<ProductEntity> entities){
		List<ProductDTO> result = new ArrayList<>();
		for(ProductEntity item : entities) {
			result.add(toDto(item));
		}	
		return result;
	}
	
	public ProductEntity toEntity(ProductDTO dto, ProductEntity oldEntity) {
		oldEntity.setMasp(dto.getMasp());
		oldEntity.setName(dto.getName());
		oldEntity.setImage(dto.getImage());
		oldEntity.setShortDescription(dto.getShortDescription());
		oldEntity.setCost(dto.getCost());
		oldEntity.setCategory(categoryService.findOneByCode(dto.getCategoryCode()));
		return oldEntity;
	}

	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		entity.setMasp(dto.getMasp());
		entity.setName(dto.getName());
		entity.setImage(dto.getImage());
		entity.setShortDescription(dto.getShortDescription());
		entity.setCost(dto.getCost());
		entity.setCategory(categoryService.findOneByCode(dto.getCategoryCode()));
		return entity;
	}
}
