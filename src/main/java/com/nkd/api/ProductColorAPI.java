package com.nkd.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.nkd.converter.ProductColorConverter;
import com.nkd.dto.ProductColorDTO;
import com.nkd.dto.ProductDTO;
import com.nkd.service.IProductColorService;

@Transactional
@RestController
@RequestMapping("/api/productColor")
public class ProductColorAPI {

	@Autowired
	private IProductColorService productColorService;

	@Autowired
	private ProductColorConverter productColorConverter;

	@GetMapping
	public List<ProductColorDTO> findAll() {
		return productColorConverter.toListDto(productColorService.findAll());
	}

	@GetMapping("/{masp}")
	public List<ProductColorDTO> findByMasp(@PathVariable String masp) {
		return productColorConverter.toListDto(productColorService.findAllProductByMasp(masp));
	}

	@GetMapping("/{masp}/{codeColor}")
	public ProductColorDTO findByMaspAndColor(@PathVariable String masp, @PathVariable String codeColor) {
		return productColorConverter.toDto(productColorService.findOneByMaspAndCodeColor(masp, codeColor));
	}

	@Transactional
	@PostMapping(value = { "/{masp}" })
	public ProductColorDTO create(@PathVariable String masp,
			@RequestPart("productColor") ProductColorDTO productColor) {
		productColor.setProduct(new ProductDTO(masp));
		return productColorService.save(productColor);
	}

	@Transactional
	@PutMapping(value = { "/{masp}/{codeColor}" })
	public ProductColorDTO update(@PathVariable String masp, @PathVariable String codeColor,
			@RequestPart("productColor") ProductColorDTO productColor) {
		productColor.setProduct(new ProductDTO(masp));
		productColorService.delete(masp, codeColor);
		return productColorService.save(productColor);
	}
	
	@DeleteMapping("/{masp}/{codeColor}")
	public void delete(@PathVariable String masp, @PathVariable(required = false) String codeColor) {
		productColorService.delete(masp, codeColor);
	}
	
	@DeleteMapping("/{masp}")
	public void delete(@PathVariable String masp, @RequestBody String[] codeColors) {
		productColorService.delete(masp, codeColors);
	}
}
