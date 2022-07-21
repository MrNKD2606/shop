package com.nkd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findOneByUserName(String item);

	UserEntity findOneByUserNameAndStatus(String username, int i);
	
}
