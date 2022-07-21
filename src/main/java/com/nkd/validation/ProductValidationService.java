package com.nkd.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.nkd.repository.ProductRepository;

@Service
public class ProductValidationService {
	
	@Autowired
	private ProductRepository productRepository;

	public String validateProduct(String masp, String maspNew) {
        String message = "";
        StringUtils.equals(masp, maspNew);
        productRepository.existsByMasp(maspNew);
        if(!StringUtils.equals(masp, maspNew) && productRepository.existsByMasp(maspNew)) {
        	message = "Mã sản phẩm đã tồn tại ";
        }
        return message;
    }
}
