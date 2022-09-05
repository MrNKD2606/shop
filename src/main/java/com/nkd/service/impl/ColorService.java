package com.nkd.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.converter.ColorConverter;
import com.nkd.dto.ColorDTO;
import com.nkd.entity.Color;
import com.nkd.entity.ProductColor;
import com.nkd.repository.ColorRepository;
import com.nkd.repository.ProductColorRepository;
import com.nkd.service.IColorService;

@Service
public class ColorService implements IColorService {

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private ColorConverter colorConverter;

	@Autowired
	private ProductColorRepository productColorRepository;

	@Override
	public Color findOneByCode(String colorCode) {
		return colorRepository.findOneByCode(colorCode);
	}

	@Override
	public List<Color> findAll() {
		return colorRepository.findAll();
	}

	@Override
	public Color findOne(long idC) {
		return colorRepository.findOne(idC);
	}

	@Override
	public Set<ColorDTO> getListColorOK(String masp, String codeColor) {
		// Lấy danh sách Color của mã sả phẩm 'masp'
		List<ProductColor> list = productColorRepository.findAllByProductMasp(masp);
		
		Set<String> code = new HashSet<>();
		for (ProductColor item : list) {
			code.add(item.getColor().getCode());
		}
		// Danh sách Color không thuộc danh sách của masp
		List<Color> colorsOK = colorRepository.findByCodeNotIn(code);

		if (codeColor != null) {
			Color color = findOneByCode(codeColor);
			colorsOK.add(color);
		}
		if (list.size() == 0) {
			colorsOK = findAll();
		}
		return colorConverter.toSetDto(colorsOK);
	}

}
