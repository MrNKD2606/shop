package com.nkd.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nkd.converter.CategoryConverter;
import com.nkd.converter.ColorConverter;
import com.nkd.converter.DetailProductConverter;
import com.nkd.converter.ProductConverter;
import com.nkd.dto.ColorDTO;
import com.nkd.dto.DetailProductDTO;
import com.nkd.dto.ProductColorId;
import com.nkd.dto.ProductDTO;
import com.nkd.entity.ProductColorEntity;
import com.nkd.service.ICategoryService;
import com.nkd.service.IColorService;
import com.nkd.service.IDetailProductService;
import com.nkd.service.IProductService;
import com.nkd.validation.ProductValidationService;

@Controller
public class AdminController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private CategoryConverter categoryConverter;

	@Autowired
	private IDetailProductService detailProductService;
	
	@Autowired
	private DetailProductConverter detailProductConverter;

	@Autowired
	private ColorConverter colorConverter;
	
	@Autowired
	private IColorService colorService;

	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private ProductValidationService validationService;

	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public String index(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.isEnabled());
		model.addAttribute("userDetails", userDetails);
		return "index";
	}

	@RequestMapping(value = "/admin/product", method = RequestMethod.GET)
	public String product(Model model) {
		model.addAttribute("products", productConverter.toListDto(productService.findAll()));
		return "product";
	}

	@GetMapping(value = { "/admin/showEditProduct", "/admin/showEditProduct/{masp}" })
	public String showProduct(Model model, @PathVariable(required = false) String masp) {
		ProductDTO product = new ProductDTO();
		if (masp != null) {
			product = productConverter.toDto(productService.findOneByMasp(masp));
			product.setCategories(categoryConverter.toSetDto(categoryService.findAll()));
			model.addAttribute("add", false);
		} else {
			product.setCategories(categoryConverter.toSetDto(categoryService.findAll()));
			model.addAttribute("add", true);
		}
		model.addAttribute("product", product);
		return "editProduct";
	}

	@PostMapping(value = { "/admin/editProduct", "/admin/editProduct/{masp}" })
	public String editProduct(Model model, @PathVariable(required = false) String masp,
			@ModelAttribute("product") @Valid ProductDTO product, BindingResult bindingResult) {
		if (masp != null) {
			product.setId(productService.findOneByMasp(masp).getId());
		}
		
		String err = validationService.validateProduct(masp, product.getMasp());
		if (!err.isEmpty()) {
	        ObjectError error = new ObjectError("globalError", err);
	        bindingResult.addError(error);
	    }
		if(bindingResult.hasErrors()) {
			return "editProduct";
		}
		productService.save(product);
		return "redirect:/admin/product";
	}

	@GetMapping(value = "/admin/detailProduct/{masp}")
	public String showListDetailProduct(Model model, @PathVariable String masp) {
		model.addAttribute("detailProducts",detailProductConverter.toListDto(detailProductService.findAllProductByMasp(masp)));
		return "detailProduct";
	}

	@GetMapping(value = {"/admin/showEditDetailProduct/{masp}", "/admin/showEditDetailProduct/{masp}/{codeColor}"})
	public String showEditDetailProduct(Model model, @PathVariable String masp, @PathVariable(required = false) String codeColor) {
		DetailProductDTO detailProduct = new DetailProductDTO();
		if(codeColor != null) {
			//long idProduct = productService.findOneByMasp(masp).getId();
			//long idColor = colorService.findOneByCode(codeColor).getId();
			detailProduct = detailProductConverter.toDto(detailProductService.findOneByMaspAndCodeColor(masp, codeColor));
			Set<ColorDTO> c = new HashSet<>();
			for (ProductColorEntity item : detailProductService.findAllProductByMasp(masp)) {
				c.add(colorConverter.toDto(item.getColor()));
			}
			Set<ColorDTO> list = colorConverter.toSetDto(colorService.getListColorOK(c));
			list.add(colorConverter.toDto(colorService.findOneByCode(codeColor)));
			detailProduct.setColorsOK(list);
			model.addAttribute("add", false);
		} else {
			detailProduct = new DetailProductDTO(detailProductConverter.toDto(productService.findOneByMasp(masp)));
			if(detailProduct.getColors().size() != 0) {
				detailProduct.setColorsOK(colorConverter.toSetDto(colorService.getListColorOK(detailProduct.getColors())));
			} else {
				detailProduct.setColorsOK(colorConverter.toSetDto(colorService.findAll()));
			}
			model.addAttribute("add", true);
		}
		model.addAttribute("detailProduct", detailProduct);
		return "editDetailProduct";
	}
	
	@PostMapping(value = {"/admin/editDetailProduct/{masp}", "/admin/editDetailProduct/{masp}/{codeColor}"})
	public String editDetailProduct(Model model, @PathVariable String masp, @PathVariable(required = false) String codeColor, @ModelAttribute("detailProduct") DetailProductDTO detailProduct) {
		detailProduct.setMasp(masp);
		if(codeColor != null) {
			detailProduct.setId(new ProductColorId(colorService.findOneByCode(codeColor).getId(), productService.findOneByMasp(masp).getId()));
		} 
		detailProductService.save(detailProduct);
		return "redirect:/admin/detailProduct/{masp}";
	}

}
