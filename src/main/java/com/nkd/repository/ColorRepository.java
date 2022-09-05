package com.nkd.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long>{

	List<Color> findByCodeNotIn(Set<String> code);

	Color findOneByCode(String colorCode);
}
