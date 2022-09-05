package com.nkd.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nkd.dto.CategoryDTO;
import com.nkd.dto.DetailProductDTO;
import com.nkd.entity.Category;
import com.nkd.entity.Product;
import com.nkd.entity.ProductColor;
import com.nkd.entity.ProductColorId;
import com.nkd.form.DetailProductForm;
import com.nkd.service.IColorService;
import com.nkd.service.IProductService;

@Component
public class DetailProductConverter {
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<DetailProductDTO> toListDto(List<ProductColor> entities){
//		List<DetailProductDTO> result = new ArrayList<>();
//		for(ProductColor item : entities) {
//			result.add(toDto(item));
//		}
//		return result;
		Converter<Category, CategoryDTO> convert = c -> c.getSource() == null ? null 
				: new CategoryDTO(c.getSource().getCode(), c.getSource().getName());
		this.modelMapper.typeMap(ProductColor.class, DetailProductDTO.class).addMappings(mapper -> {
			mapper.skip(DetailProductDTO::setId);
			mapper.map(src -> src.getProduct().getMasp(), DetailProductDTO::setMasp);
			mapper.map(src -> src.getProduct().getName(), DetailProductDTO::setName);
			mapper.map(src -> src.getProduct().getCost(), DetailProductDTO::setCost);
			mapper.map(src -> src.getProduct().getShortDescription(), DetailProductDTO::setShortDescription);
			mapper.using(convert).map(src -> src.getProduct().getCategory(), DetailProductDTO::setCategory);
		});
		return entities.stream().map(item -> modelMapper.map(item, DetailProductDTO.class)).collect(Collectors.toList());
	}
	
	public DetailProductDTO toDto(Product entity) {
//		DetailProductDTO dto = new DetailProductDTO();
//		dto.setName(entity.getName());
//		dto.setMasp(entity.getMasp());
//		dto.setShortDescription(entity.getShortDescription());
//		dto.setCost(entity.getCost());
//		dto.setCategory(new CategoryDTO(entity.getCategory().getCode(), entity.getCategory().getName()));
//		return dto;
		
		return this.modelMapper.map(entity, DetailProductDTO.class);
	}

	public DetailProductDTO toDto(ProductColor entity) {
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
		Converter<Category, CategoryDTO> convert = c -> c.getSource() == null ? null 
				: new CategoryDTO(c.getSource().getCode(), c.getSource().getName());
		this.modelMapper.typeMap(ProductColor.class, DetailProductDTO.class).addMappings(mapper -> {
			mapper.skip(DetailProductDTO::setId);
			mapper.map(src -> src.getProduct().getMasp(), DetailProductDTO::setMasp);
			mapper.map(src -> src.getProduct().getName(), DetailProductDTO::setName);
			mapper.map(src -> src.getProduct().getCost(), DetailProductDTO::setCost);
			mapper.map(src -> src.getProduct().getShortDescription(), DetailProductDTO::setShortDescription);
			mapper.using(convert).map(src -> src.getProduct().getCategory(), DetailProductDTO::setCategory);
		});
		return this.modelMapper.map(entity, DetailProductDTO.class);
	}
	
	public ProductColor toEntity(DetailProductDTO dto) {
		ProductColor entity = new ProductColor();
		entity.setPercent(dto.getPercent());
		entity.setQuantity(dto.getQuantity());
		entity.setColor(colorService.findOneByCode(dto.getColor().getCode()));
		entity.setProduct(productService.findOneByMasp(dto.getMasp()));
		ProductColorId id = new ProductColorId(entity.getProduct().getId(), entity.getColor().getId());
		entity.setId(id);
		return entity;
		
//		Converter<String, Color> converterColor = c -> c.getSource() == null ? null 
//				: colorService.findOneByCode(c.getSource());
//		Converter<String, Product> converProduct = c -> c.getSource() == null ? null 
//				: productService.findOneByMasp(c.getSource());
//		Converter<DetailProductDTO, ProductColorId> convert = c -> c.getSource() == null ? null 
//				: new ProductColorId(productService.findOneByMasp(c.getSource().getMasp()).getId(), colorService.findOneByCode(c.getSource().getColor().getCode()).getId());
//		this.modelMapper.typeMap(DetailProductDTO.class, ProductColor.class)
//		.addMappings(mapper -> {
//			mapper.using(converterColor).map(src -> src.getColor().getCode(), ProductColor::setColor);
//			mapper.using(converProduct).map(DetailProductDTO::getMasp, ProductColor::setProduct);
//			mapper.using(convert).map(DetailProductDTO.class, ProductColor::setId);
//		});
//		return this.modelMapper.map(dto, ProductColor.class);
	}

	public ProductColor toEntity(DetailProductForm dto, ProductColor entity) {
		entity.setPercent(dto.getPercent());
		entity.setQuantity(dto.getQuantity());
		entity.setColor(colorService.findOneByCode(dto.getColorCode()));
		entity.setProduct(productService.findOneByMasp(dto.getMasp()));
		entity.setId(new ProductColorId(entity.getProduct().getId(), entity.getColor().getId()));
		return entity;
	}
	
}
