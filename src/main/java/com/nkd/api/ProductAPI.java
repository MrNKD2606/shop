package com.nkd.api;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductAPI {

//	@Autowired
//	private ProductRepository productRepository;
//	
//	@Autowired
//	private ProductConverter productConverter;

//	@GetMapping(value = "/product")
//	public ProductOutput showProduct() {
//		ProductOutput result = new ProductOutput();
//		List<ProductEntity> entities = new ArrayList<>();
//		entities = (productRepository.findAll());
//		List<ProductDTO> listProduct = new ArrayList<>();
//		for(ProductEntity item : entities) {
//			listProduct.add(productConverter.toDto(item));
//		}
//		result.setListResult(listProduct);
//		return result;
//	}
}
