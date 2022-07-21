package com.nkd.service;

import java.util.List;

import com.nkd.dto.DetailProductDTO;
import com.nkd.entity.ProductColorEntity;

public interface IDetailProductService {

	void save(DetailProductDTO detailProduct);

	List<ProductColorEntity> findAllProductByMasp(String masp);

	ProductColorEntity findOneByMaspAndCodeColor(String masp, String codeColor);

}
