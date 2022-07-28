package com.nkd.service;

import java.util.List;

import com.nkd.entity.ProductColorEntity;

public interface IDetailProductService {

	void save(ProductColorEntity entity);

	List<ProductColorEntity> findAllProductByMasp(String masp);

	ProductColorEntity findOneByMaspAndCodeColor(String masp, String codeColor);

}
