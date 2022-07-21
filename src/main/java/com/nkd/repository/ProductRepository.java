package com.nkd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	boolean existsByMasp(String masp);

	ProductEntity findOneByMasp(String masp);

}