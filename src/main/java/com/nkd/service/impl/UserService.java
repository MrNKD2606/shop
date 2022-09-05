package com.nkd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkd.converter.UserConverter;
import com.nkd.dto.UserDTO;
import com.nkd.entity.Role;
import com.nkd.entity.Account;
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
		Account entity = new Account();
		if (dto.getId() != null) {
			Account oldEntity = userRepository.findOne(dto.getId());
			entity = userConverter.toEntity(dto, oldEntity);
		} else {
			entity = userConverter.toEntity(dto);
		}
		List<Role> roles = new ArrayList<>();
		for (String item : dto.getRoles()) {
			Role role = new Role();
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
