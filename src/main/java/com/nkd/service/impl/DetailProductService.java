package com.nkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.converter.DetailProductConverter;
import com.nkd.dto.DetailProductDTO;
import com.nkd.entity.ProductColor;
import com.nkd.entity.ProductColorId;
import com.nkd.form.DetailProductForm;
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

	@Autowired
	private DetailProductConverter detailProductConverter;

	@Autowired
	private IDetailProductService detailProductService;

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
	public DetailProductForm getProduct(String masp, String codeColor) {
		DetailProductDTO detailProduct = new DetailProductDTO();
		if (codeColor != null) {
			detailProduct = detailProductConverter
					.toDto(detailProductService.findOneByMaspAndCodeColor(masp, codeColor));
		} else {
			detailProduct = detailProductConverter.toDto(productService.findOneByMasp(masp));
		}
		return new DetailProductForm(detailProduct);
	}

	@Override
	public void delete(String masp, String codeColor) {
		long idProduct = productService.findOneByMasp(masp).getId();
		long idColor = colorService.findOneByCode(codeColor).getId();
		productColorRepository.delete(new ProductColorId(idProduct, idColor));
	}

	@Override
	public void save(DetailProductDTO detailProductDTO) {
		productColorRepository.save(detailProductConverter.toEntity(detailProductDTO));
	}

}
