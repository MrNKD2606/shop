package com.nkd.api;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewAPI {

//	@Autowired
//	private INewService newService;
//	
//	@GetMapping(value = "/new")
//	public NewOutput showNew(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
//		NewOutput result = new NewOutput();
//		//Gửi trang cần xem vào NewOutput
//		result.setPage(page);
//		Pageable pageable = new PageRequest(page - 1, limit);
//		result.setListResult(newService.findAll(pageable));
//		result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
//		return result;
//	}
//	
//	@PostMapping(value = "/new")
//	public NewDTO createNew(@RequestBody NewDTO model) {
//		return newService.save(model);
//	}
//	
//	@PutMapping(value = "/new/{id}")
//	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") long id) {
//		model.setId(id);
//		return newService.save(model);
//	}
//	
//	@DeleteMapping(value = "/new")
//	public void deleteNew(@RequestBody long[] ids) {
//		newService.delete(ids);
//	}
}