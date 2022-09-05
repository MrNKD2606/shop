package com.nkd.service;

import java.util.List;
import java.util.Set;

import com.nkd.dto.ColorDTO;
import com.nkd.entity.Color;

public interface IColorService {

	Color findOneByCode(String colorCode);

	List<Color> findAll();

	Color findOne(long idC);

	Set<ColorDTO> getListColorOK(String masp, String codeColor);

}
