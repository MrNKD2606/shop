package com.nkd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.Account;

public interface UserRepository extends JpaRepository<Account, Long> {

	Optional<Account> findOneByUserName(String item);

	Account findOneByUserNameAndStatus(String username, int i);
	
}
