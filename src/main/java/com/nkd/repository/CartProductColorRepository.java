package com.nkd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.CartProductColorEntity;
import com.nkd.entity.CartProductColorId;

public interface CartProductColorRepository extends JpaRepository<CartProductColorEntity, CartProductColorId>{

	List<CartProductColorEntity> findAllByCart_Id(long idCart);

}
