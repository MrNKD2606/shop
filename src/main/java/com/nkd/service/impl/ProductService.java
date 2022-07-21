package com.nkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkd.converter.ProductConverter;
import com.nkd.dto.ProductDTO;
import com.nkd.entity.ProductEntity;
import com.nkd.repository.ProductRepository;
import com.nkd.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductEntity> findAll() {
		return productRepository.findAll();
	}

	@Transactional
	@Override
	public ProductEntity save(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		if (dto.getId() != null) {
			entity = productRepository.findOne(dto.getId());
			entity = productConverter.toEntity(dto, entity);
		} else {
			entity = productConverter.toEntity(dto);
		}

		return productRepository.save(entity);
	}

	@Override
	public ProductEntity findOneByMasp(String masp) {
		return productRepository.findOneByMasp(masp);
	}

}
