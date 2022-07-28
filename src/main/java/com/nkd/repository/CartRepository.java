package com.nkd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

	CartEntity findOneByMaCart(String macart);

	List<CartEntity> findAllByStatus(int status);

	List<CartEntity> findAllByCreatedDateBetweenAndStatus(Date start, Date end, int status);

}
