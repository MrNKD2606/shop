package com.nkd.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.dto.ColorDTO;
import com.nkd.entity.ColorEntity;
import com.nkd.repository.ColorRepository;
import com.nkd.service.IColorService;

@Service
public class ColorService implements IColorService{	
	
	@Autowired
	private ColorRepository colorRepository;

	@Override
	public List<ColorEntity> getListColorOK(Set<ColorDTO> list) {
		Set<String> code = new HashSet<>();
		for(ColorDTO item : list) {
			code.add(item.getCode());
		}
		return colorRepository.findByCodeNotIn(code);
		
	}

	@Override
	public ColorEntity findOneByCode(String colorCode) {
		return colorRepository.findOneByCode(colorCode);
	}

	@Override
	public List<ColorEntity> findAll() {
		return colorRepository.findAll();
	}

	@Override
	public ColorEntity findOne(long idC) {
		return colorRepository.findOne(idC);
	}

}
