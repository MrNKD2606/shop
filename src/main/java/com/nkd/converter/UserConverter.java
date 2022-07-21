package com.nkd.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nkd.dto.UserDTO;
import com.nkd.entity.RoleEntity;
import com.nkd.entity.UserEntity;

@Component
public class UserConverter {

	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setUserName(dto.getUserName());
		entity.setFullName(dto.getFullName());
		entity.setPassword(dto.getPassword());
		return entity;
	}
	
	public UserEntity toEntity(UserDTO dto, UserEntity oldEntity) {
		oldEntity.setUserName(dto.getUserName());
		oldEntity.setFullName(dto.getFullName());
		oldEntity.setPassword(dto.getPassword());
		return oldEntity;
	}

	public UserDTO toDto(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setUserName(entity.getUserName());
		dto.setFullName(entity.getFullName());
		dto.setPassword(entity.getPassword());
		dto.setStatus(entity.getStatus());
		List<String> list = new ArrayList<>();
		for(RoleEntity item : entity.getRoles()) {
			String roleCode = item.getCode();
			list.add(roleCode);
		}
		dto.setRoles(list);
		return dto;
	}
}
