package com.nkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.converter.DetailProductConverter;
import com.nkd.dto.DetailProductDTO;
import com.nkd.entity.ProductColorEntity;
import com.nkd.entity.ProductColorId;
import com.nkd.repository.ProductColorRepository;
import com.nkd.service.IColorService;
import com.nkd.service.IDetailProductService;
import com.nkd.service.IProductService;

@Service
public class DetailProductService implements IDetailProductService {

	@Autowired
	private DetailProductConverter detailProductConverter;

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
	public void save(DetailProductDTO dto) {
		ProductColorEntity entity = new ProductColorEntity();
		if (dto.getId() != null) {
			entity = productColorRepository
					.findOne(new ProductColorId(dto.getId().getProductId(), dto.getId().getColorId()));
			long idColorOld = entity.getId().getColorId();
			entity = detailProductConverter.toEntity(dto, entity);
			productColorRepository.delete(new ProductColorId(dto.getId().getProductId(), idColorOld));
		} else {
			entity = detailProductConverter.toEntity(dto);

		}
		productColorRepository.save(entity);
	}

	@Override
	public ProductColorEntity findOneByMaspAndCodeColor(String masp, String codeColor) {
		long idProduct = productService.findOneByMasp(masp).getId();
		long idColor = colorService.findOneByCode(codeColor).getId();
		return productColorRepository.findOne(new ProductColorId(idProduct, idColor));
	}

}
