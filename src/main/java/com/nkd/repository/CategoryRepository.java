package com.nkd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	CategoryEntity findOneByCode(String categoryCode);

}
