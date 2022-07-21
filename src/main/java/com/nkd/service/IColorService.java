package com.nkd.service;

import java.util.List;
import java.util.Set;

import com.nkd.dto.ColorDTO;
import com.nkd.entity.ColorEntity;

public interface IColorService {

	List<ColorEntity> getListColorOK(Set<ColorDTO> list);

	ColorEntity findOneByCode(String colorCode);

	List<ColorEntity> findAll();

	ColorEntity findOne(long idC);

}
