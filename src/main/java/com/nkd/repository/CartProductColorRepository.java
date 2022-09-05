package com.nkd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.CartProductColor;
import com.nkd.entity.CartProductColorId;

public interface CartProductColorRepository extends JpaRepository<CartProductColor, CartProductColorId>{

	List<CartProductColor> findAllByCart_Id(long idCart);

}
