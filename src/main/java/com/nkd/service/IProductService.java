package com.nkd.service;

import java.util.List;

import com.nkd.dto.ProductDTO;
import com.nkd.entity.Product;

public interface IProductService {
	
	List<Product> findAll();
	
	Product save(ProductDTO dto);
	
	Product findOneByMasp(String masp);

	List<Product> findAllByCategoryCode(String categoryCode);

	void delete(String masp);

	List<Product> findAllByStatus(int status);

}
