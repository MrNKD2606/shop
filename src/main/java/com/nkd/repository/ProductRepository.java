package com.nkd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByMasp(String masp);

	Product findOneByMasp(String masp);

	List<Product> findAllByCategoryCode(String categoryCode);

	List<Product> findAllByStatus(int status);

}