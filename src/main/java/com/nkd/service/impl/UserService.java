package com.nkd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.converter.UserConverter;
import com.nkd.dto.UserDTO;
import com.nkd.entity.RoleEntity;
import com.nkd.entity.UserEntity;
import com.nkd.repository.RoleRepository;
import com.nkd.repository.UserRepository;
import com.nkd.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDTO save(UserDTO dto) {
		UserEntity entity = new UserEntity();
		if (dto.getId() != null) {
			UserEntity oldEntity = userRepository.findOne(dto.getId());
			entity = userConverter.toEntity(dto, oldEntity);
		} else {
			entity = userConverter.toEntity(dto);
		}
		List<RoleEntity> roles = new ArrayList<>();
		for (String item : dto.getRoles()) {
			RoleEntity role = new RoleEntity();
			role = roleRepository.findOneByCode(item).get();
			roles.add(role);
		}
		entity.setRoles(roles);
		entity = userRepository.save(entity);
		return userConverter.toDto(entity);
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			userRepository.delete(item);
		}
	}

}
