package com.nkd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findOneByCode(String categoryCode);

}
