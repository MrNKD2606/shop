package com.nkd.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
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

import com.nkd.converter.CartConverter;
import com.nkd.converter.CategoryConverter;
import com.nkd.converter.DetailProductConverter;
import com.nkd.converter.ProductConverter;
import com.nkd.dto.CartDTO;
import com.nkd.dto.CategoryDTO;
import com.nkd.dto.ColorDTO;
import com.nkd.dto.DetailProductDTO;
import com.nkd.dto.ProductDTO;
import com.nkd.entity.Cart;
import com.nkd.form.DetailProductForm;
import com.nkd.form.ProductForm;
import com.nkd.service.ICartService;
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
	private IColorService colorService;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductValidationService validationService;

	@Autowired
	private CartConverter cartConverter;

	@Autowired
	private ICartService cartService;

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
		model.addAttribute("products", productConverter.toListDto(productService.findAllByStatus(1)));
		return "product";
	}

	@GetMapping(value = { "/admin/carts", "/admin/carts/{status}" })
	public String carts(Model model, @PathVariable(required = false) Integer status) {
		if (status == null) {
			model.addAttribute("carts", cartConverter.toListDto(cartService.findAll()));
		} else if (status == 1) {
			model.addAttribute("carts", cartConverter.toListDto(cartService.findAllByStatus(1)));
		} else if (status == 0) {
			model.addAttribute("carts", cartConverter.toListDto(cartService.findAllByStatus(0)));
		}
		return "adminCart";
	}

	@GetMapping(value = { "/admin/viewCart/{maCart}" })
	public String cart(Model model, @PathVariable String maCart) {
		CartDTO dto = new CartDTO();
		dto = cartConverter.toDto(cartService.findOneByMaCart(maCart));
		model.addAttribute("cart", dto);
		return "viewCart";
	}

	@GetMapping(value = "/admin/pay/{maCart}")
	public String payCart(Model model, @PathVariable String maCart) {
		cartService.payCart(cartService.findOneByMaCart(maCart));
		return "redirect:/admin/carts";
	}

	@GetMapping(value = { "/admin/showEditProduct", "/admin/showEditProduct/{masp}" })
	public String showProduct(Model model, @PathVariable(required = false) String masp) {
		ProductForm productForm = new ProductForm();
		if (masp != null) {
			productForm = new ProductForm(productConverter.toDto(productService.findOneByMasp(masp)));
			model.addAttribute("add", false);
		} else {
			model.addAttribute("add", true);
		}
		Set<CategoryDTO> cartegorys = categoryConverter.toSetDto(categoryService.findAll());
		model.addAttribute("productForm", productForm);
		model.addAttribute("cartegorys", cartegorys);
		return "editProduct";
	}

	@PostMapping(value = { "/admin/product", "/admin/product/{masp}" })
	public String editProduct(Model model, @PathVariable(required = false) String masp,
			@ModelAttribute("productForm") @Valid ProductForm productForm, BindingResult bindingResult) {
		ProductDTO product = new ProductDTO(productForm);
		if (masp != null) {
			product.setId(productService.findOneByMasp(masp).getId());
		} else {
			product.setStatus(1);
		}

		String err = validationService.validateProduct(masp, productForm.getMasp());
		if (!err.isEmpty()) {
			ObjectError error = new ObjectError("globalError", err);
			bindingResult.addError(error);
		}
		if (bindingResult.hasErrors()) {
			return "editProduct";
		}
		productService.save(product);
		return "redirect:/admin/product";
	}

	@GetMapping("/admin/product/{masp}")
	public String deleteProduct(Model model, @PathVariable(required = false) String masp) {
		productService.delete(masp);
		return "redirect:/admin/product";
	}

	@GetMapping(value = "/admin/detailProduct/{masp}")
	public String showListDetailProduct(Model model, @PathVariable String masp) {
		model.addAttribute("detailProducts",
				detailProductConverter.toListDto(detailProductService.findAllProductByMasp(masp)));
		return "detailProduct";
	}

	@GetMapping(value = { "/admin/showEditDetailProduct/{masp}", "/admin/showEditDetailProduct/{masp}/{codeColor}" })
	public String showEditDetailProduct(Model model, @PathVariable String masp,
			@PathVariable(required = false) String codeColor) {
		DetailProductForm detailProductForm = detailProductService.getProduct(masp, codeColor);
		Set<ColorDTO> colorsOK = colorService.getListColorOK(masp, codeColor);
		if (codeColor != null) {
			model.addAttribute("add", false);
		} else {
			model.addAttribute("add", true);
		}

		model.addAttribute("listColorOK", colorsOK);
		model.addAttribute("detailProductForm", detailProductForm);
		return "editDetailProduct";
	}

	@Transactional
	@PostMapping(value = { "/admin/editDetailProduct/{masp}", "/admin/editDetailProduct/{masp}/{codeColor}" })
	public String editDetailProduct(Model model, 
			@PathVariable(required = false) String codeColor,
			@ModelAttribute("detailProduct") DetailProductForm detailProduct) {
		
		if (codeColor != null) {
			detailProductService.delete(detailProduct.getMasp(), codeColor);
		} 
		detailProductService.save(new DetailProductDTO(detailProduct));
		return "redirect:/admin/detailProduct/{masp}";
	}
	
	@GetMapping("/admin/deleteProductColor/{masp}/{codeColor}")
	public String deleteProductColor(Model model, @PathVariable String masp, @PathVariable String codeColor) {
		detailProductService.delete(masp, codeColor);
		return "redirect:/admin/detailProduct/{masp}";
	}

	@GetMapping(value = { "/admin/stats" })
	public String stats(Model model) {
		LocalDate today = LocalDate.now();
		LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
		List<Long> listTotal = new ArrayList<>();
		List<String> listMonth = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			List<Cart> entities = cartService.findAllByCreatedDateBetweenAndStatus(firstDayOfMonth, lastDayOfMonth, 0);
			long sumPay = 0;
			for (Cart item : entities) {
				sumPay = sumPay + cartService.totalCart(cartConverter.toDto(item).getListOrder());
			}
			String month = "Tháng " + firstDayOfMonth.getMonthValue();
			listMonth.add(month);
			listTotal.add(sumPay);
			firstDayOfMonth = firstDayOfMonth.plusMonths(-1);
			lastDayOfMonth = lastDayOfMonth.plusMonths(-1);
		}
		Collections.reverse(listTotal);
		Collections.reverse(listMonth);
		Long max = Collections.max(listTotal);
		max = (max / 100000 + 1) * 1000000;
		model.addAttribute("listTotal", listTotal);
		model.addAttribute("listMonth", listMonth);
		model.addAttribute("maxTotal", max);
		return "stats";
	}
}
