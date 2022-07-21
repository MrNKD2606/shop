package com.nkd.service;

import com.nkd.dto.UserDTO;

public interface IUserService {

	UserDTO save(UserDTO dto);

	void delete(long[] ids);

}
