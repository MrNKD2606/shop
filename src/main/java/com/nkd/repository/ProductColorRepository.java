package com.nkd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nkd.entity.ProductColorEntity;
import com.nkd.entity.ProductColorId;

public interface ProductColorRepository extends JpaRepository<ProductColorEntity, ProductColorId>{

	
	
	List<ProductColorEntity> findAllByProductMasp(String masp);
	
	@Modifying
	@Query(value = "update productes_colors u set u.color_id = ? where u.color_id = ? and u.product_id = ?", 
	  nativeQuery = true)
	void update(@Param("colorId") Long colorId, @Param("colorId") Long colorIdOld, @Param("productId") Long productId);

}
