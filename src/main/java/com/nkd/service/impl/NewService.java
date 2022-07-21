package com.nkd.service.impl;

import org.springframework.stereotype.Service;

import com.nkd.service.INewService;

@Service
public class NewService implements INewService{

//	@Autowired
//	private NewConverter newConverter;
//	
//	@Autowired
//	private NewRepository newRepository;
//	
//	@Autowired
//	private CategoryRepository categoryRepository;
//	
//	@Override
//	public NewDTO save(NewDTO newDTO) {
//		NewEntity entity = new NewEntity();
//		if (newDTO.getId() != null) {
//			//Lấy thông tin entity từ id cần thay đổi thông tin
//			NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
//			entity = newConverter.toEntity(newDTO, oldNewEntity);
//		} else {
//			//Gán giá trị DTO -> Entity để lưu vào database
//			entity = newConverter.toEntity(newDTO);
//		}
//		//Lấy categoryEntity từ categoryCode để lưu id_category vào news
//		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
//		//Gán category vừa tìm theo categoryCode vào entity để lưu vào database
//		entity.setCategory(categoryEntity);
//		//Lưu entity vào trong database
//		entity = newRepository.save(entity);
//		//return một NewDTO
//		return newConverter.toDto(entity);
//	}
//
//	@Override
//	public void delete(long[] ids) {
//		for(long item : ids) {
//			newRepository.delete(item);
//		}
//		
//	}
//
//	@Override
//	public List<NewDTO> findAll(Pageable pageable) {
//		List<NewDTO> results = new ArrayList<>();
//		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
//		for(NewEntity item : entities) {
//			NewDTO newDTO = newConverter.toDto(item);
//			results.add(newDTO);
//		}
//		return results;
//	}
//
//	@Override
//	public int totalItem() {
//		return (int) newRepository.count();
//	}

}
