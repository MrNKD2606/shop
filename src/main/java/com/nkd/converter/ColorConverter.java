package com.nkd.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nkd.dto.ColorDTO;
import com.nkd.entity.ColorEntity;

@Component
public class ColorConverter {
	
	public ColorDTO toDto(ColorEntity entity) {
		ColorDTO dto = new ColorDTO();
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		return dto;
	}
	
	public Set<ColorDTO> toSetDto(List<ColorEntity> entities){
		Set<ColorDTO> result = new HashSet<>();
		for(ColorEntity item : entities) {
			result.add(toDto(item));
		}
		return result;
	}

}
