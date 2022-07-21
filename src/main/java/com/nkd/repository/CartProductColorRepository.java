package com.nkd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.CartProductColorEntity;
import com.nkd.entity.CartProductColorId;

public interface CartProductColorRepository extends JpaRepository<CartProductColorEntity, CartProductColorId>{

}
