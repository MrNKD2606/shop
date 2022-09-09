package com.nkd.service;

import java.util.List;

import com.nkd.dto.ProductColorDTO;
import com.nkd.entity.ProductColor;

public interface IProductColorService {

	List<ProductColor> findAllProductByMasp(String masp);

	ProductColor findOneByMaspAndCodeColor(String masp, String codeColor);

	void delete(String masp, String codeColor);

	ProductColorDTO save(ProductColorDTO result);

	List<ProductColor> findAll();

	void delete(String masp, String[] codeColors);

}
