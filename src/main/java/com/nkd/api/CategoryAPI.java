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
import org.springframework.web.bind.annotation.RestController;

import com.nkd.converter.CategoryConverter;
import com.nkd.dto.CategoryDTO;
import com.nkd.entity.Category;
import com.nkd.service.ICategoryService;

@Transactional
@RestController
@RequestMapping("/api/category")
public class CategoryAPI {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private CategoryConverter categoryConverter;

	@GetMapping
	public List<CategoryDTO> findAll() {
		return categoryConverter.toListDto(categoryService.findAll());
	}
	
	@GetMapping("/{code}")
	public CategoryDTO findByCode(@PathVariable String code) {
		return categoryConverter.toDto(categoryService.findOneByCode(code));
	}
	
	@PostMapping
	public CategoryDTO create(@RequestBody CategoryDTO dto) {
		return categoryConverter.toDto(categoryService.save(categoryConverter.toEntity(dto)));
	}
	
	@PutMapping("/{code}")
	public CategoryDTO update(@RequestBody CategoryDTO dto, @PathVariable String code) {
		Category entity = categoryService.findOneByCode(code) ;
		return categoryConverter.toDto(categoryService.save(categoryConverter.toEntity(dto, entity)));
	}
	
	@DeleteMapping("{code}")
	public void update(@PathVariable String code) {
		categoryService.delete(code);
	}

}
