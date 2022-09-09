package com.nkd.api;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.nkd.converter.ProductConverter;
import com.nkd.dto.ProductDTO;
import com.nkd.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductAPI {

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductConverter productConverter;

	@GetMapping
	public List<ProductDTO> findAll() {
		return productConverter.toListDto(productService.findAll());
	}

	@GetMapping("/{masp}")
	public ProductDTO findByMasp(@PathVariable String masp) {
		return productConverter.toDto(productService.findOneByMasp(masp));
	}

	@PostMapping(value = { "/" })
	public ProductDTO create(@RequestPart(value = "image", required = false) MultipartFile image,
			@RequestPart("product") ProductDTO product) {
		if (image != null) {
			product.setImage(image.getOriginalFilename());
		}
		return productConverter.toDto(productService.save(product));
	}

	@PutMapping(value = { "/{masp}" })
	public ProductDTO update(@PathVariable String masp,
			@RequestPart(value = "image", required = false) MultipartFile image,
			@RequestPart("product") ProductDTO product) {
		if (image != null) {
			product.setImage(image.getOriginalFilename());
		}
		product.setId(productService.findOneByMasp(masp).getId());
		return productConverter.toDto(productService.save(product));
	}

	@DeleteMapping
	public void deleteProduct(@RequestBody String masp) {
		productService.delete(masp);
	}
}
