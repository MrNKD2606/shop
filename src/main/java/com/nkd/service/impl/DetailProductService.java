package com.nkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.entity.ProductColorEntity;
import com.nkd.entity.ProductColorId;
import com.nkd.repository.ProductColorRepository;
import com.nkd.service.IColorService;
import com.nkd.service.IDetailProductService;
import com.nkd.service.IProductService;

@Service
public class DetailProductService implements IDetailProductService {

	@Autowired
	private ProductColorRepository productColorRepository;

	@Autowired
	private IProductService productService;

	@Autowired
	private IColorService colorService;

	@Override
	public List<ProductColorEntity> findAllProductByMasp(String masp) {
		return productColorRepository.findAllByProductMasp(masp);
	}

	@Override
	public void save(ProductColorEntity entity) {
		productColorRepository.save(entity);
	}

	@Override
	public ProductColorEntity findOneByMaspAndCodeColor(String masp, String codeColor) {
		long idProduct = productService.findOneByMasp(masp).getId();
		long idColor = colorService.findOneByCode(codeColor).getId();
		return productColorRepository.findOne(new ProductColorId(idProduct, idColor));
	}

}
