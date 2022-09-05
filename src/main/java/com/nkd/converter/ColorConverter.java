package com.nkd.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nkd.dto.ColorDTO;
import com.nkd.entity.Color;
import com.nkd.entity.ProductColor;

@Component
public class ColorConverter {
	
	public ColorDTO toDto(Color entity) {
		ColorDTO dto = new ColorDTO();
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		return dto;
	}
	
	public Set<ColorDTO> toSetDto(List<Color> entities){
		Set<ColorDTO> result = new HashSet<>();
		for(Color item : entities) {
			result.add(toDto(item));
		}
		return result;
	}

	public Set<ColorDTO> toListDto(List<ProductColor> entities) {
		Set<ColorDTO> result = new HashSet<>();
		for(ProductColor item : entities) {
			result.add(toDto(item.getColor()));
		}
		return result;
	}
	
	

}
