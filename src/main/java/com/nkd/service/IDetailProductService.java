package com.nkd.service;

import java.util.List;

import com.nkd.dto.DetailProductDTO;
import com.nkd.entity.ProductColor;
import com.nkd.form.DetailProductForm;

public interface IDetailProductService {

	List<ProductColor> findAllProductByMasp(String masp);

	ProductColor findOneByMaspAndCodeColor(String masp, String codeColor);

	DetailProductForm getProduct(String masp, String codeColor);

	void delete(String masp, String codeColor);

	void save(DetailProductDTO detailProductDTO);

}
