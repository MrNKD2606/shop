package com.nkd.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nkd.dto.UserDTO;
import com.nkd.entity.Role;
import com.nkd.entity.Account;

@Component
public class UserConverter {

	public Account toEntity(UserDTO dto) {
		Account entity = new Account();
		entity.setUserName(dto.getUserName());
		entity.setFullName(dto.getFullName());
		entity.setPassword(dto.getPassword());
		return entity;
	}
	
	public Account toEntity(UserDTO dto, Account oldEntity) {
		oldEntity.setUserName(dto.getUserName());
		oldEntity.setFullName(dto.getFullName());
		oldEntity.setPassword(dto.getPassword());
		return oldEntity;
	}

	public UserDTO toDto(Account entity) {
		UserDTO dto = new UserDTO();
		dto.setUserName(entity.getUserName());
		dto.setFullName(entity.getFullName());
		dto.setPassword(entity.getPassword());
		dto.setStatus(entity.getStatus());
		List<String> list = new ArrayList<>();
		for(Role item : entity.getRoles()) {
			String roleCode = item.getCode();
			list.add(roleCode);
		}
		dto.setRoles(list);
		return dto;
	}
}
