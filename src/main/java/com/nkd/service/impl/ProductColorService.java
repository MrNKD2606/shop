package com.nkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.converter.ProductColorConverter;
import com.nkd.dto.ProductColorDTO;
import com.nkd.entity.ProductColor;
import com.nkd.entity.ProductColorId;
import com.nkd.repository.ProductColorRepository;
import com.nkd.service.IColorService;
import com.nkd.service.IProductColorService;
import com.nkd.service.IProductService;

@Service
public class ProductColorService implements IProductColorService {

	@Autowired
	private ProductColorRepository productColorRepository;

	@Autowired
	private IProductService productService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private ProductColorConverter productColorConverter;

	@Override
	public List<ProductColor> findAll() {
		return productColorRepository.findAll();
	}

	@Override
	public List<ProductColor> findAllProductByMasp(String masp) {
		return productColorRepository.findAllByProductMasp(masp);
	}

	@Override
	public ProductColor findOneByMaspAndCodeColor(String masp, String codeColor) {
		long idProduct = productService.findOneByMasp(masp).getId();
		long idColor = colorService.findOneByCode(codeColor).getId();
		return productColorRepository.findOne(new ProductColorId(idProduct, idColor));
	}

	@Override
	public void delete(String masp, String codeColor) {
		long idProduct = productService.findOneByMasp(masp).getId();
		long idColor = colorService.findOneByCode(codeColor).getId();
		productColorRepository.delete(new ProductColorId(idProduct, idColor));
	}

	@Override
	public void delete(String masp, String[] codeColors) {
		long idProduct = productService.findOneByMasp(masp).getId();
		for (String item : codeColors) {
			long idColor = colorService.findOneByCode(item).getId();
			productColorRepository.delete(new ProductColorId(idProduct, idColor));
		}
	}

	@Override
	public ProductColorDTO save(ProductColorDTO dto) {
		return productColorConverter.toDto(productColorRepository.save(productColorConverter.toEntity(dto)));
	}

}
