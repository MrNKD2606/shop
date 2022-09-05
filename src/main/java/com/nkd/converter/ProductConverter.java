package com.nkd.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nkd.dto.ProductDTO;
import com.nkd.entity.Category;
import com.nkd.entity.Product;
import com.nkd.service.ICategoryService;

@Component
public class ProductConverter {
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductDTO toDto(Product entity) {
//		ProductDTO dto = new ProductDTO();
//		dto.setId(entity.getId());
//		dto.setMasp(entity.getMasp());
//		dto.setName(entity.getName());
//		dto.setCost(entity.getCost());
//		dto.setShortDescription(entity.getShortDescription());
//		dto.setCategory(categoryConverter.toDto(entity.getCategory()));
//		dto.setImage(entity.getImage());
//		return dto;
		return modelMapper.map(entity, ProductDTO.class);
	}
	
	public List<ProductDTO> toListDto(List<Product> entities){
//		List<ProductDTO> result = new ArrayList<>();
//		for(Product item : entities) {
//			result.add(toDto(item));
//		}	
//		return result;
		return entities.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
	}
	
	public Product toEntity(ProductDTO dto, Product oldEntity) {
//		oldEntity.setMasp(dto.getMasp());
//		oldEntity.setName(dto.getName());
//		if(dto.getImage() != null) {
//			oldEntity.setImage(dto.getImage());
//		}
//		oldEntity.setShortDescription(dto.getShortDescription());
//		oldEntity.setCost(dto.getCost());
//		oldEntity.setCategory(categoryService.findOneByCode(dto.getCategory().getCode()));
//		return oldEntity;
		
//		dto.setCreatedDate(oldEntity.getCreatedDate());
//		dto.setCreatedBy(oldEntity.getCreatedBy());
//		dto.setStatus(oldEntity.getStatus());
//		if(dto.getImage().equals("")) {
//			dto.setImage(oldEntity.getImage());
//		}
		
		Converter<String, Category> converterCategory = c -> c.getSource() == null ? null 
				: categoryService.findOneByCode(c.getSource());
		this.modelMapper.typeMap(ProductDTO.class, Product.class)
		.setProvider(p -> oldEntity)
		.addMappings(mapper -> {
			mapper.using(converterCategory).map(src -> src.getCategory().getCode(), Product::setCategory);
			mapper.when(Conditions.isNull()).skip(Product::setStatus);
			mapper.when(Conditions.isNull()).skip(Product::setCreatedBy);
			mapper.when(Conditions.isNull()).skip(Product::setCreatedDate);
			mapper.when(Conditions.isNull()).skip(Product::setImage);
			mapper.when(Conditions.isNotNull()).map(ProductDTO::getImage, Product::setImage);
		});
		return this.modelMapper.map(dto, Product.class);
	}

	public Product toEntity(ProductDTO dto) {
//		Product entity = new Product();
//		entity.setMasp(dto.getMasp());
//		entity.setName(dto.getName());
//		entity.setImage(dto.getImage());	
//		entity.setShortDescription(dto.getShortDescription());
//		entity.setCost(dto.getCost());
//		entity.setCategory(categoryService.findOneByCode(dto.getCategory().getCode()));
//		entity.setStatus(dto.getStatus());
//		return entity;
		Converter<String, Category> converterCategory = c -> c.getSource() == null ? null 
				: categoryService.findOneByCode(c.getSource());
		this.modelMapper.typeMap(ProductDTO.class, Product.class).addMappings(mapper -> {
			mapper.map(src -> src.getImage(), Product::setImage);
			mapper.using(converterCategory).map(src -> src.getCategory().getCode(), Product::setCategory);
		});
		return this.modelMapper.map(dto, Product.class);
	}
}
