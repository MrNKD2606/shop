package com.nkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkd.converter.ProductConverter;
import com.nkd.dto.ProductDTO;
import com.nkd.entity.Product;
import com.nkd.repository.ProductRepository;
import com.nkd.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Transactional
	@Override
	public Product save(ProductDTO dto) {
		Product entity = new Product();
		if (dto.getId() != null) {
			entity = productRepository.findOne(dto.getId());
			entity = productConverter.toEntity(dto, entity);
		} else {
			entity = productConverter.toEntity(dto);
		}

		return productRepository.save(entity);
	}

	@Override
	public Product findOneByMasp(String masp) {
		return productRepository.findOneByMasp(masp);
	}

	@Override
	public List<Product> findAllByCategoryCode(String categoryCode) {
		return productRepository.findAllByCategoryCode(categoryCode);
	}

	@Override
	public void delete(String masp) {
		Product entity = productRepository.findOneByMasp(masp);
		entity.setStatus(0);
		productRepository.save(entity);
	}

	@Override
	public List<Product> findAllByStatus(int status) {
		return productRepository.findAllByStatus(status);
	}

}
