package com.nkd.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

import com.nkd.converter.CartConverter;
import com.nkd.converter.CategoryConverter;
import com.nkd.converter.ColorConverter;
import com.nkd.converter.DetailProductConverter;
import com.nkd.converter.ProductConverter;
import com.nkd.dto.CartDTO;
import com.nkd.dto.CategoryDTO;
import com.nkd.dto.ColorDTO;
import com.nkd.dto.DetailProductDTO;
import com.nkd.dto.ProductDTO;
import com.nkd.entity.CartEntity;
import com.nkd.entity.ProductColorEntity;
import com.nkd.entity.ProductColorId;
import com.nkd.repository.ProductColorRepository;
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
	private ColorConverter colorConverter;
	
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
	
	@Autowired
	private ProductColorRepository productColorRepository;

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
	
	@GetMapping(value = { "/admin/carts", "/admin/carts/{status}" })
	public String carts(Model model, @PathVariable(required = false) Integer status) {
		if(status == null) {
			model.addAttribute("carts", cartConverter.toListDto(cartService.findAll()));
		} else if(status == 1) {
			model.addAttribute("carts", cartConverter.toListDto(cartService.findAllByStatus(1)));
		} else if(status == 0) {
			model.addAttribute("carts", cartConverter.toListDto(cartService.findAllByStatus(0)));
		}
		return "adminCart";
	}
	
	@GetMapping(value = {"/admin/viewCart/{maCart}"})
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
		ProductDTO product = new ProductDTO();
		if (masp != null) {
			product = productConverter.toDto(productService.findOneByMasp(masp));
			model.addAttribute("add", false);
		} else {
			model.addAttribute("add", true);
		}
		Set<CategoryDTO> carts = categoryConverter.toSetDto(categoryService.findAll());
		model.addAttribute("product", product);
		model.addAttribute("carts", carts);
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
		Set<ColorDTO> colorsOK = new HashSet<>();
		if(codeColor != null) {
			detailProduct = detailProductConverter.toDto(detailProductService.findOneByMaspAndCodeColor(masp, codeColor));
			Set<ColorDTO> c = new HashSet<>();
			for (ProductColorEntity item : detailProductService.findAllProductByMasp(masp)) {
				c.add(colorConverter.toDto(item.getColor()));
			}
			Set<ColorDTO> list = colorConverter.toSetDto(colorService.getListColorOK(c));
			list.add(colorConverter.toDto(colorService.findOneByCode(codeColor)));
			colorsOK = list;
			model.addAttribute("add", false);
		} else {
			detailProduct = new DetailProductDTO(detailProductConverter.toDto(productService.findOneByMasp(masp)));
			if(detailProduct.getColors().size() != 0) {
				colorsOK = colorConverter.toSetDto(colorService.getListColorOK(detailProduct.getColors()));
			} else {
				colorsOK = colorConverter.toSetDto(colorService.findAll());
			}
			model.addAttribute("add", true);
		}
		model.addAttribute("listColorOK", colorsOK);
		model.addAttribute("detailProduct", detailProduct);
		return "editDetailProduct";
	}
	
	@PostMapping(value = {"/admin/editDetailProduct/{masp}", "/admin/editDetailProduct/{masp}/{codeColor}"})
	public String editDetailProduct(Model model, @PathVariable String masp, @PathVariable(required = false) String codeColor, @ModelAttribute("detailProduct") DetailProductDTO detailProduct) {
		detailProduct.setMasp(masp);
		ProductColorEntity entity = new ProductColorEntity();
		if(codeColor != null) {
			long idProduct = productService.findOneByMasp(masp).getId();
			long idColor = colorService.findOneByCode(codeColor).getId();
			entity = productColorRepository.findOne(new ProductColorId(idProduct, idColor));
			entity = detailProductConverter.toEntity(detailProduct, entity);
			productColorRepository.delete(new ProductColorId(idProduct, idColor));
		} else {
			entity = detailProductConverter.toEntity(detailProduct);
		}
		detailProductService.save(entity);
		return "redirect:/admin/detailProduct/{masp}";
	}

	@GetMapping(value = { "/admin/stats" })
	public String stats(Model model) {
		LocalDate today = LocalDate.now();
		LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
		List<Long> listTotal = new ArrayList<>();
		List<String> listMonth = new ArrayList<>();
		for(int i = 0; i < 12; i++) {
			List<CartEntity> entities = cartService.findAllByCreatedDateBetweenAndStatus(firstDayOfMonth, lastDayOfMonth, 0);
			long sumPay = 0;
			for(CartEntity item : entities) {
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
		max = (max/100000 + 1) * 1000000;
		model.addAttribute("listTotal", listTotal);
		model.addAttribute("listMonth", listMonth);
		model.addAttribute("maxTotal", max);
		return "stats";
	}
}
