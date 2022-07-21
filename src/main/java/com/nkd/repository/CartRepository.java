package com.nkd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

	CartEntity findOneByMaCart(String macart);

}
