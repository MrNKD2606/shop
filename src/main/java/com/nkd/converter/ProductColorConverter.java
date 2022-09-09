package com.nkd.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nkd.dto.ProductColorDTO;
import com.nkd.entity.Color;
import com.nkd.entity.Product;
import com.nkd.entity.ProductColor;
import com.nkd.service.IColorService;
import com.nkd.service.IProductService;

@Component
public class ProductColorConverter {

	@Autowired
	private IColorService colorService;

	@Autowired
	private IProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	public List<ProductColorDTO> toListDto(List<ProductColor> entities) {
//		List<DetailProductDTO> result = new ArrayList<>();
//		for(ProductColor item : entities) {
//			result.add(toDto(item));
//		}
//		return result;
//		Converter<Category, CategoryDTO> convert = c -> c.getSource() == null ? null
//				: new CategoryDTO(c.getSource().getCode(), c.getSource().getName());
//		this.modelMapper.typeMap(ProductColor.class, ProductColorDTO.class).addMappings(mapper -> {
//			mapper.skip(ProductColorDTO::setId);
//			mapper.map(src -> src.getProduct().getMasp(), DetailProductDTO::setMasp);
//			mapper.map(src -> src.getProduct().getName(), DetailProductDTO::setName);
//			mapper.map(src -> src.getProduct().getCost(), DetailProductDTO::setCost);
//			mapper.map(src -> src.getProduct().getShortDescription(), DetailProductDTO::setShortDescription);
//			mapper.using(convert).map(src -> src.getProduct().getCategory(), DetailProductDTO::setCategory);
//		});
		return entities.stream().map(item -> modelMapper.map(item, ProductColorDTO.class)).collect(Collectors.toList());
	}

	public ProductColorDTO toDto(ProductColor entity) {
//		DetailProductDTO dto = new DetailProductDTO();
//		dto.setMasp(entity.getProduct().getMasp());
//		dto.setName(entity.getProduct().getName());
//		dto.setCost(entity.getProduct().getCost());
//		dto.setShortDescription(entity.getProduct().getShortDescription());
//		dto.setPercent(entity.getPercent());
//		dto.setQuantity(entity.getQuantity());
//		dto.setCategory(new CategoryDTO(entity.getProduct().getCategory().getCode(), entity.getProduct().getCategory().getName()));
//		dto.setColor(new ColorDTO(entity.getColor().getName(), entity.getColor().getCode()));
//		return dto;

		return this.modelMapper.map(entity, ProductColorDTO.class);
	}

	public ProductColor toEntity(ProductColorDTO dto) {
//		ProductColor entity = new ProductColor();
//		entity.setPercent(dto.getPercent());
//		entity.setQuantity(dto.getQuantity());
//		entity.setColor(colorService.findOneByCode(dto.getColor().getCode()));
//		entity.setProduct(productService.findOneByMasp(dto.getProduct().getMasp()));
//		ProductColorId id = new ProductColorId(entity.getProduct().getId(), entity.getColor().getId());
//		entity.setId(id);
//		return entity;

		Converter<String, Product> converterProduct = c -> c.getSource() == null ? null
				: productService.findOneByMasp(c.getSource());
		Converter<String, Color> converterColor = c -> c.getSource() == null ? null
				: colorService.findOneByCode(c.getSource());
		this.modelMapper.typeMap(ProductColorDTO.class, ProductColor.class).addMappings(mapper -> {
			mapper.using(converterProduct).map(src -> src.getProduct().getMasp(), ProductColor::setProduct);
			mapper.using(converterColor).map(src -> src.getColor().getCode(), ProductColor::setColor);
		});
		return new ProductColor(this.modelMapper.map(dto, ProductColor.class));
	}

}
