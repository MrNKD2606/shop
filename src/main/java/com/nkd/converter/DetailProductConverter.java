package com.nkd.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nkd.dto.ColorDTO;
import com.nkd.dto.DetailProductDTO;
import com.nkd.dto.ProductColorId;
import com.nkd.entity.ProductColorEntity;
import com.nkd.entity.ProductEntity;
import com.nkd.service.IColorService;
import com.nkd.service.IProductService;

@Component
public class DetailProductConverter {
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IProductService productService;
	
	public List<DetailProductDTO> toListDto(List<ProductColorEntity> entity){
		List<DetailProductDTO> result = new ArrayList<>();
		for(ProductColorEntity item : entity) {
			result.add(toDto(item));
		}
		return result;
	}
	
	public DetailProductDTO toDto(ProductEntity entity) {
		DetailProductDTO dto = new DetailProductDTO();
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
		dto.setMasp(entity.getMasp());
		dto.setShortDescription(entity.getShortDescription());
		dto.setCost(entity.getCost());
		dto.setCategoryCode(entity.getCategory().getCode());
		dto.setCategoryName(entity.getCategory().getName());
		Set<ColorDTO> list = new HashSet<>();
		for(ProductColorEntity item : entity.getproductColor()) {
			ColorDTO temp = new ColorDTO();
			temp.setId(item.getColor().getId());
			temp.setName(item.getColor().getName());
			temp.setCode(item.getColor().getCode());
			list.add(temp);
		}
		dto.setColors(list);
		return dto;
	}

	public DetailProductDTO toDto(ProductColorEntity entity) {
		DetailProductDTO dto = new DetailProductDTO();
		dto.setId(new ProductColorId(entity.getId().getColorId(), entity.getId().getProductId()));
		dto.setName(entity.getProduct().getName());
		dto.setImage(entity.getProduct().getImage());
		dto.setMasp(entity.getProduct().getMasp());
		dto.setShortDescription(entity.getProduct().getShortDescription());
		dto.setCost(entity.getProduct().getCost());
		dto.setCategoryCode(entity.getProduct().getCategory().getCode());
		dto.setCategoryName(entity.getProduct().getCategory().getName());
		dto.setColor(entity.getColor().getName());
		dto.setColorCode(entity.getColor().getCode());
		dto.setPercent(entity.getPercent());
		dto.setPrice(entity.getProduct().getCost() + (entity.getPercent() * entity.getProduct().getCost() / 100));
		dto.setQuantity(entity.getQuantity());
		return dto;
	}
	
	public ProductColorEntity toEntity(DetailProductDTO dto) {
		ProductColorEntity entity = new ProductColorEntity();
		entity.setPercent(dto.getPercent());
		entity.setQuantity(dto.getQuantity());
		entity.setColor(colorService.findOneByCode(dto.getColorCode()));
		entity.setProduct(productService.findOneByMasp(dto.getMasp()));
		com.nkd.entity.ProductColorId id = new com.nkd.entity.ProductColorId(entity.getProduct().getId(), entity.getColor().getId());
		entity.setId(id);
		return entity;
	}

	public ProductColorEntity toEntity(DetailProductDTO dto, ProductColorEntity entity) {
		entity.setPercent(dto.getPercent());
		entity.setQuantity(dto.getQuantity());
		entity.setColor(colorService.findOneByCode(dto.getColorCode()));
		entity.setProduct(productService.findOneByMasp(dto.getMasp()));
		entity.setId(new com.nkd.entity.ProductColorId(entity.getProduct().getId(), entity.getColor().getId()));
		return entity;
	}
	
}
