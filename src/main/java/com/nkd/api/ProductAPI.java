package com.nkd.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nkd.converter.ProductConverter;
import com.nkd.dto.ProductDTO;
import com.nkd.service.IProductService;

@RestController
@RequestMapping("/api")
public class ProductAPI {

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductConverter productConverter;

	@GetMapping("/admin/product")
	public List<ProductDTO> getProductList() {
		return productConverter.toListDto(productService.findAll());
	}

	@PostMapping("/admin/product")
	public void editProduct(@RequestBody ProductDTO product, @RequestParam MultipartFile image) {
		//product.setImage(image);
		productService.save(product);
	}
}
