package com.nkd.service;

import java.util.List;

import com.nkd.dto.ProductDTO;
import com.nkd.entity.ProductEntity;

public interface IProductService {
	
	List<ProductEntity> findAll();
	
	ProductEntity save(ProductDTO dto);
	
	ProductEntity findOneByMasp(String masp);

	List<ProductEntity> findAllByCategoryCode(String categoryCode);

}
